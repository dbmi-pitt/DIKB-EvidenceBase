WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value, m.inferred_value from ohdsi.method m),
s AS (
SELECT ca.id, c.concept_name, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.subject = True AND q.concept_code!= ''
JOIN public.concept c ON c.concept_code = q.concept_code AND LOWER(c.vocabulary_id) = '@vocabularyid1'),
o AS (
SELECT ca.id, c.concept_name, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.object = True AND q.concept_code!= ''
JOIN public.concept c ON c.concept_code = q.concept_code AND LOWER(c.vocabulary_id) = '@vocabularyid2')
SELECT method.entered_value, method.inferred_value, count(*) as cnts
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
WHERE (o.concept_code = '@conceptcode1' AND o.qualifier_role_concept_code = '@roleconceptcode1' AND s.concept_code = '@conceptcode2')
OR (s.concept_code = '@conceptcode1' AND s.qualifier_role_concept_code = '@roleconceptcode1' AND o.concept_code = '@conceptcode2' )
GROUP BY method.entered_value, method.inferred_value;

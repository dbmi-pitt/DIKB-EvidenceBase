WITH method AS (
SELECT mp_claim_id, m.entered_value as method from ohdsi.method m),

s AS (
SELECT ca.id, cb.claim_text, c.concept_name, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, q.qualifier_role_vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.subject = True AND q.concept_code!= ''
JOIN public.concept c ON c.concept_code = q.concept_code),

o AS (
SELECT ca.id, c.concept_name, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, q.qualifier_role_vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.object = True AND q.concept_code!= ''
JOIN public.concept c ON c.concept_code = q.concept_code),

p AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.predicate = True)

SELECT distinct method.mp_claim_id, s.claim_text, method.method, s.concept_name as subject, p.qvalue as relationship, o.concept_name as object, s.qualifier_role_concept_code, o.qualifier_role_concept_code
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
JOIN p ON p.id = method.mp_claim_id
WHERE method.method = '@method'
AND (s.concept_name= '@conceptname1' AND s.qualifier_role_concept_code = '@roleconceptcode1' AND o.concept_name = '@conceptname2')
OR (o.concept_name = '@conceptname1' AND o.qualifier_role_concept_code = '@roleconceptcode1' AND s.concept_name = '@conceptname2');

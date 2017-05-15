WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value, m.inferred_value from ohdsi.method m),
p AS (
SELECT ca.id, cb.claim_text, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.subject = True
AND q.qvalue = '@precipitant'
),
o AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.object = True
AND q.qvalue = '@object')
SELECT method.entered_value, method.inferred_value, count(*) as cnts
from method
JOIN p ON p.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
GROUP BY method.entered_value, method.inferred_value;

WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value as method from ohdsi.method m),
p AS (
SELECT ca.id, cb.claim_text, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.subject = True
AND cb.label LIKE '%interact%with%'),
o AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.object = True
AND cb.label LIKE '%interact%with%')
SELECT method.mp_claim_id, method.mp_data_index, p.label, p.claim_text, method.method, p.qvalue as precipitant, p.concept_code as p_concept_code, p.qualifier_role_concept_code as p_role_concept_code, o.qvalue as object, 
o.concept_code as o_concept_code, o.qualifier_role_concept_code as o_role_concept_code
from method
JOIN p ON p.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
WHERE method.method = 'DDI clinical trial';

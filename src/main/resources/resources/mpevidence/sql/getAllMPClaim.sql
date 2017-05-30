WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value as method from ohdsi.method m),
s AS (
SELECT ca.id, cb.claim_text, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.subject = True),
o AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.object = True)
SELECT method.mp_claim_id, method.mp_data_index, s.label, s.claim_text, method.method, s.qvalue as subject, s.concept_code as s_concept_code, s.qualifier_role_concept_code as s_role_concept_code, o.qvalue as object, 
o.concept_code as o_concept_code, o.qualifier_role_concept_code as o_role_concept_code
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id;

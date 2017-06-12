WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value as method from ohdsi.method m),
s AS (
SELECT ca.id, cb.claim_text, q.qvalue, c.concept_name, q.concept_code, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.subject = True AND q.concept_code!= '' AND q.vocabulary_id = '44819104'
JOIN public.concept c ON c.concept_code = q.concept_code),
o AS (
SELECT ca.id, q.qvalue, c.concept_name, q.concept_code, q.qualifier_role_concept_code
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id AND q.object = True AND q.concept_code!= '' AND q.vocabulary_id = '44819104'
JOIN public.concept c ON c.concept_code = q.concept_code)
SELECT method.mp_claim_id, method.mp_data_index, s.label, s.claim_text, method.method, s.concept_name as subject, s.concept_code as s_concept_code, s.qualifier_role_concept_code as s_role_concept_code, o.concept_name as object, 
o.concept_code as o_concept_code, o.qualifier_role_concept_code as o_role_concept_code
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id;

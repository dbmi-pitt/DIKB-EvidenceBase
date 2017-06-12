WITH s AS (
SELECT ca.id, c.concept_name, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
JOIN public.concept c ON c.concept_code = q.concept_code AND c.vocabulary_id = 'RxNorm'
WHERE q.subject = True
AND q.concept_code!= '' AND q.vocabulary_id = '44819104'),
o AS (
SELECT ca.id, c.concept_name, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, cb.label
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
JOIN public.concept c ON c.concept_code = q.concept_code AND c.vocabulary_id = 'RxNorm'
WHERE q.object = True
AND q.concept_code!= '' AND q.vocabulary_id = '44819104')
SELECT distinct s.concept_name as subject, s.concept_code as s_concept_code, s.qualifier_role_concept_code as s_role_concept_code, o.concept_name as object, 
o.concept_code as o_concept_code, o.qualifier_role_concept_code as o_role_concept_code
FROM s JOIN o ON o.id = s.id
WHERE s.concept_name = '@drugconceptname' OR o.concept_code = '@drugconceptname';

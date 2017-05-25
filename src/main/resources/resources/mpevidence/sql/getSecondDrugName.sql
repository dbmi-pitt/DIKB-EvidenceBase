WITH p AS (
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
SELECT distinct p.qvalue as subject, p.concept_code as p_concept_code, p.qualifier_role_concept_code as p_role_concept_code, o.qvalue as object, 
o.concept_code as o_concept_code, o.qualifier_role_concept_code as o_role_concept_code
FROM p JOIN o ON o.id = p.id
WHERE p.qvalue = '@drugname' OR o.qvalue = '@drugname';

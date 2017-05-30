-- get claim by specified user entered method and two drug names

WITH method AS (
SELECT mp_claim_id, m.entered_value as method from ohdsi.method m),

s AS (
SELECT ca.id, cb.claim_text, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, q.qualifier_role_vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.subject = True),

o AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id, q.qualifier_role_concept_code, q.qualifier_role_vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.object = True),

p AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.predicate = True)

SELECT distinct method.mp_claim_id, s.claim_text, method.method, s.qvalue as subject, p.qvalue as relationship, o.qvalue as object, s.qualifier_role_concept_code, o.qualifier_role_concept_code
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
JOIN p ON p.id = method.mp_claim_id
WHERE method.method = 'DDI clinical trial'
AND (s.qvalue = 'ketoconazole' AND o.qvalue = 'alitretinoin')
OR (s.qvalue = 'alitretinoin' AND o.qvalue = 'ketoconazole');

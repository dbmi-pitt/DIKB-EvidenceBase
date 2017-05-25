WITH method AS (
SELECT mp_claim_id, mp_data_index, m.entered_value as method from ohdsi.method m),

s AS (
SELECT ca.id, cb.claim_text, q.qvalue, q.concept_code, q.vocabulary_id
FROM ohdsi.mp_claim_annotation ca 
JOIN ohdsi.oa_claim_body cb on cb.is_oa_body_of = ca.id
JOIN ohdsi.qualifier q on q.claim_body_id = cb.id
WHERE q.subject = True),

o AS (
SELECT ca.id, q.qvalue, q.concept_code, q.vocabulary_id
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

SELECT method.mp_claim_id, method.mp_data_index, s.claim_text, method.method, s.qvalue as subject, p.qvalue as relationship, o.qvalue as object
from method
JOIN s ON s.id = method.mp_claim_id
JOIN o ON o.id = method.mp_claim_id
JOIN p ON p.id = method.mp_claim_id
WHERE method.method = '@method'
AND (s.qvalue = '@drugname1' AND o.qvalue = '@drugname2')
OR (s.qvalue = '@drugname2' AND o.qvalue = '@drugname1');


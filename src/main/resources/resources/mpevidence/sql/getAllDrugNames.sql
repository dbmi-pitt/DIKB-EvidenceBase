WITH qualifier AS (
SELECT DISTINCT q.concept_code FROM ohdsi.qualifier q 
WHERE q.predicate = False 
AND q.concept_code != '' AND q.vocabulary_id = '44819104'),
vocab AS (
SELECT * FROM public.concept c WHERE c.vocabulary_id = 'RxNorm')
SELECT vocab.concept_name, vocab.concept_code 
FROM qualifier JOIN vocab ON qualifier.concept_code = vocab.concept_code;

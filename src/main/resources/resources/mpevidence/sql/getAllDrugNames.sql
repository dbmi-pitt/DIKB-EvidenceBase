-- query all qualifiers with concept code and concept name as ingredient or brand name
WITH qua AS (
SELECT DISTINCT q.concept_code, q.vocabulary_id FROM ohdsi.qualifier q 
WHERE q.predicate = False AND q.concept_code != ''),
cpt AS (
SELECT DISTINCT c.concept_code, c.concept_name, c.concept_class_id
FROM public.concept c 
WHERE c.concept_class_id = 'Brand Name' OR c.concept_class_id = 'Ingredient' OR c.concept_class_id = 'Domain'
)
SELECT cpt.concept_name, vocab.vocabulary_id, cpt.concept_code, cpt.concept_class_id
FROM cpt JOIN qua ON qua.concept_code = cpt.concept_code
JOIN public.vocabulary vocab ON qua.vocabulary_id = vocab.vocabulary_concept_id;
 

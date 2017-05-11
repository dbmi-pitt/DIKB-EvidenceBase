WITH o_qualifier AS (select claim_body_id, qvalue 
from ohdsi.qualifier where predicate = False and qualifier_role_concept_code = 'DIDEO_00000012'),
p_qualifier AS (select claim_body_id, qvalue 
from ohdsi.qualifier where predicate = False and qualifier_role_concept_code = 'DIDEO_00000013')
SELECT distinct o_qualifier.qvalue AS object_name, p_qualifier.qvalue AS precipitant_name
FROM o_qualifier join p_qualifier ON o_qualifier.claim_body_id = p_qualifier.claim_body_id;

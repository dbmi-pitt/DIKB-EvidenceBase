SELECT mann.mp_claim_id, mann.mp_data_index, mann.ev_supports, mbody.material_type, mf.material_field_type, mf.value_as_string
FROM ohdsi.mp_material_annotation mann
JOIN ohdsi.oa_material_body mbody ON mann.has_body = mbody.id
JOIN ohdsi.material_field mf ON mbody.id = mf.material_body_id
WHERE mann.mp_claim_id = '@claimId';

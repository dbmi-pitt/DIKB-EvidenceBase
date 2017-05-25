SELECT dann.mp_claim_id, dann.mp_data_index, dbody.data_type, df.data_field_type, df.value_as_string
FROM ohdsi.mp_data_annotation dann
JOIN ohdsi.oa_data_body dbody ON dann.has_body = dbody.id
JOIN ohdsi.data_field df ON dbody.id = df.data_body_id
WHERE dann.mp_claim_id = '@claimId';

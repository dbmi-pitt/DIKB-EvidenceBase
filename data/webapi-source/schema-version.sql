--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = ohdsi, pg_catalog;

--
-- Data for Name: schema_version; Type: TABLE DATA; Schema: ohdsi; Owner: rdb20
--

INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (1, 1, '1.0.0.1', 'schema-create spring batch', 'SQL', 'V1.0.0.1__schema-create_spring_batch.sql', 1254272413, 'rdb20', '2016-04-02 10:27:46.0607', 1474, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (2, 2, '1.0.0.2', 'schema-create jpa', 'SQL', 'V1.0.0.2__schema-create_jpa.sql', -303952977, 'rdb20', '2016-04-02 10:27:47.941905', 45, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (3, 3, '1.0.0.3', 'cohort definition persistence', 'SQL', 'V1.0.0.3__cohort_definition_persistence.sql', 745871256, 'rdb20', '2016-04-02 10:27:48.002324', 145, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (4, 4, '1.0.0.3.1', 'cohort generation', 'SQL', 'V1.0.0.3.1__cohort_generation.sql', -27753051, 'rdb20', '2016-04-02 10:27:48.162248', 78, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (5, 5, '1.0.0.3.2', 'alter foreign keys', 'SQL', 'V1.0.0.3.2__alter_foreign_keys.sql', -1786579696, 'rdb20', '2016-04-02 10:27:48.259278', 6, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (6, 6, '1.0.0.4', 'cohort analysis results', 'SQL', 'V1.0.0.4__cohort_analysis_results.sql', -1284569990, 'rdb20', '2016-04-02 10:27:48.283884', 182, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (7, 7, '1.0.0.4.1', 'heracles heel', 'SQL', 'V1.0.0.4.1__heracles_heel.sql', 1425835180, 'rdb20', '2016-04-02 10:27:48.485163', 4, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (9, 8, '1.0.0.4.3', 'heracles index', 'SQL', 'V1.0.0.4.3__heracles_index.sql', -897785632, 'rdb20', '2016-04-02 10:27:48.505497', 207, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (10, 9, '1.0.0.5', 'feasability tables', 'SQL', 'V1.0.0.5__feasability_tables.sql', -524018977, 'rdb20', '2016-04-02 10:27:48.726659', 168, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (11, 10, '1.0.0.5.1', 'alter foreign keys', 'SQL', 'V1.0.0.5.1__alter_foreign_keys.sql', -528865590, 'rdb20', '2016-04-02 10:27:48.908769', 5, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (12, 11, '1.0.0.6.1', 'schema-create laertes', 'SQL', 'V1.0.0.6.1__schema-create_laertes.sql', -593183069, 'rdb20', '2016-04-02 10:27:48.926086', 426, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (13, 12, '1.0.0.6.2', 'schema-create laertes', 'SQL', 'V1.0.0.6.2__schema-create_laertes.sql', -1447827014, 'rdb20', '2016-04-02 10:27:49.365558', 6, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (14, 13, '1.0.0.6.3', 'schema-create laertes', 'SQL', 'V1.0.0.6.3__schema-create_laertes.sql', -845635838, 'rdb20', '2016-04-02 10:27:49.385706', 9, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (15, 14, '1.0.0.6.4', 'schema-create laertes', 'SQL', 'V1.0.0.6.4__schema-create_laertes.sql', 358690557, 'rdb20', '2016-04-02 10:27:49.408575', 6, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (8, 22, '1.0.0.4.2', 'measurement types', 'SQL', 'V1.0.0.4.2__measurement_types.sql', 552008962, 'rdb20', '2017-03-15 18:34:27.136988', 65, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (17, 15, '1.0.0.7.0', 'sources.sql', 'SQL', 'V1.0.0.7.0__sources.sql.sql', -1019931789, 'rdb20', '2016-04-02 10:27:49.428773', 36, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (18, 16, '1.0.0.7.1', 'cohort multihomed support', 'SQL', 'V1.0.0.7.1__cohort_multihomed_support.sql', 1338461033, 'rdb20', '2016-04-02 10:27:49.477703', 50, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (19, 17, '1.0.0.7.2', 'feasability multihomed support.sql', 'SQL', 'V1.0.0.7.2__feasability_multihomed_support.sql.sql', -1900927535, 'rdb20', '2016-04-02 10:27:49.541824', 60, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (20, 18, '1.0.0.8', 'heracles data', 'SQL', 'V1.0.0.8__heracles_data.sql', 1626592845, 'rdb20', '2016-04-02 10:27:49.61821', 94, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (16, 23, '1.0.0.6.5', 'schema-create penelope laertes', 'SQL', 'V1.0.0.6.5__schema-create_penelope_laertes.sql', -1254272594, 'rdb20', '2017-03-15 18:34:27.268431', 479, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (21, 24, '1.0.0.9', 'shiro security', 'SQL', 'V1.0.0.9__shiro_security.sql', -1443486481, 'rdb20', '2017-03-15 18:34:27.764358', 735, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (23, 19, '1.0.1.0', 'conceptsets', 'SQL', 'V1.0.1.0__conceptsets.sql', 1596904975, 'rdb20', '2016-04-02 10:27:49.728134', 11, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (24, 20, '1.0.1.1', 'penelope', 'SQL', 'V1.0.1.1__penelope.sql', 2122743697, 'rdb20', '2016-04-02 10:27:49.757461', 145, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (25, 21, '1.0.1.1.1', 'penelope data', 'SQL', 'V1.0.1.1.1__penelope_data.sql', -1211737505, 'rdb20', '2016-04-02 10:27:49.916654', 1229, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (22, 25, '1.0.0.9.1', 'shiro security-initial values', 'SQL', 'V1.0.0.9.1__shiro_security-initial_values.sql', -1060530912, 'rdb20', '2017-03-15 18:34:28.518071', 29, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (26, 26, '1.0.1.2', 'conceptset negative controls', 'SQL', 'V1.0.1.2__conceptset_negative_controls.sql', 348844206, 'rdb20', '2017-03-15 18:34:28.565739', 50, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (27, 27, '1.0.1.3', 'conceptset generation info', 'SQL', 'V1.0.1.3__conceptset_generation_info.sql', 2101588319, 'rdb20', '2017-03-15 18:34:28.633208', 156, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (28, 28, '1.0.2.0', 'cohort feasiblity', 'SQL', 'V1.0.2.0__cohort_feasiblity.sql', 912205576, 'rdb20', '2017-03-15 18:34:28.802588', 46, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (29, 29, '1.0.3.1', 'comparative cohort analysis', 'SQL', 'V1.0.3.1__comparative_cohort_analysis.sql', -592293256, 'rdb20', '2017-03-15 18:34:28.858523', 17, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (30, 30, '1.0.4.0', 'ir analysis', 'SQL', 'V1.0.4.0__ir_analysis.sql', -1806551670, 'rdb20', '2017-03-15 18:34:28.88941', 236, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (31, 31, '1.0.4.1', 'ir dist', 'SQL', 'V1.0.4.1__ir_dist.sql', 1121776342, 'rdb20', '2017-03-15 18:34:29.140607', 6, true);
INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (32, 32, '1.0.5.0', 'rename system user to anonymous', 'SQL', 'V1.0.5.0__rename_system_user_to_anonymous.sql', -1519451184, 'rdb20', '2017-03-15 18:34:29.159378', 71, true);


--
-- PostgreSQL database dump complete
--


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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: source_daimon; Type: TABLE; Schema: ohdsi; Owner: rdb20; Tablespace: 
--

CREATE TABLE source_daimon (
    source_daimon_id integer NOT NULL,
    source_id integer NOT NULL,
    daimon_type integer NOT NULL,
    table_qualifier character varying(255) NOT NULL,
    priority integer NOT NULL
);


ALTER TABLE source_daimon OWNER TO rdb20;

--
-- Data for Name: source_daimon; Type: TABLE DATA; Schema: ohdsi; Owner: rdb20
--

INSERT INTO source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) VALUES (1, 1, 0, 'public', 1);
INSERT INTO source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) VALUES (2, 1, 1, 'public', 1);
INSERT INTO source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) VALUES (3, 1, 2, 'OHDSI', 1);
INSERT INTO source_daimon (source_daimon_id, source_id, daimon_type, table_qualifier, priority) VALUES (4, 1, 3, 'OHDSI', 1);


--
-- Name: pk_source_daimon; Type: CONSTRAINT; Schema: ohdsi; Owner: rdb20; Tablespace: 
--

ALTER TABLE ONLY source_daimon
    ADD CONSTRAINT pk_source_daimon PRIMARY KEY (source_daimon_id);


--
-- PostgreSQL database dump complete
--


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
-- Name: source; Type: TABLE; Schema: ohdsi; Owner: rdb20; Tablespace: 
--

CREATE TABLE source (
    source_id integer NOT NULL,
    source_name character varying(255) NOT NULL,
    source_key character varying(50) NOT NULL,
    source_connection character varying(8000) NOT NULL,
    source_dialect character varying(255) NOT NULL
);


ALTER TABLE source OWNER TO dikb;

--
-- Data for Name: source; Type: TABLE DATA; Schema: ohdsi; Owner: dikb
--

INSERT INTO source (source_id, source_name, source_key, source_connection, source_dialect) VALUES (1, 'DIKB', 'POSTGRES-DIKB', 'jdbc:postgresql://localhost:5432/dikb?user=<username>&password=<password>', 'postgresql');


--
-- Name: pk_source; Type: CONSTRAINT; Schema: ohdsi; Owner: dikb; Tablespace: 
--

-- ALTER TABLE ONLY source
--     ADD CONSTRAINT pk_source PRIMARY KEY (source_id);


--
-- PostgreSQL database dump complete
--


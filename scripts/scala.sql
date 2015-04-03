-- 
-- Script for database scala

CREATE TABLE persona
(
  id serial NOT NULL,
  nombre character varying NOT NULL,
  edad integer NOT NULL,
  CONSTRAINT persona_pkey PRIMARY KEY (id)
);


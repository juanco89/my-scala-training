-- Table: personas

-- DROP TABLE personas;

CREATE TABLE personas
(
  id serial NOT NULL,
  nombre text NOT NULL,
  edad integer NOT NULL,
  telefono text,
  direccion text,
  CONSTRAINT personas_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE personas OWNER TO postgres;
GRANT ALL ON TABLE personas TO postgres;

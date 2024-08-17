CREATE TABLE administration (
                                administration_id SERIAL PRIMARY KEY,
                                nom_admin VARCHAR(255) NOT NULL
);

CREATE TABLE antennes (
                          antenne_id SERIAL PRIMARY KEY,
                          admin_depot VARCHAR(255) NOT NULL,
                          admin_reception VARCHAR(255) NOT NULL
);

CREATE TABLE condition (
                           condition_id SERIAL PRIMARY KEY,
                           nom_condition VARCHAR(255) NOT NULL,
                           termes VARCHAR(255)
);

CREATE TABLE cout (
                      cout_id SERIAL PRIMARY KEY,
                      valeur DOUBLE PRECISION NOT NULL
);

CREATE TABLE delai (
                       delai_id SERIAL PRIMARY KEY,
                       duree INTEGER NOT NULL
);

CREATE TABLE famille_document (
                                  famille_id SERIAL PRIMARY KEY
);

CREATE TABLE procedure (
                           procedure_id SERIAL PRIMARY KEY,
                           titre VARCHAR(255) NOT NULL,
                           description VARCHAR(255),
                           administration_id BIGINT NOT NULL,
                           antenne_id BIGINT NOT NULL,
                           delai_id BIGINT NOT NULL,
                           cout_id BIGINT NOT NULL,
                           CONSTRAINT fk_procedure_administration FOREIGN KEY (administration_id) REFERENCES administration(administration_id),
                           CONSTRAINT fk_procedure_antenne FOREIGN KEY (antenne_id) REFERENCES antennes(antenne_id),
                           CONSTRAINT fk_procedure_delai FOREIGN KEY (delai_id) REFERENCES delai(delai_id),
                           CONSTRAINT fk_procedure_cout FOREIGN KEY (cout_id) REFERENCES cout(cout_id)
);

CREATE TABLE dossier (
                         dossier_id SERIAL PRIMARY KEY,
                         procedure_id BIGINT NOT NULL,
                         condition_id BIGINT NOT NULL,
                         CONSTRAINT fk_dossier_procedure FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id),
                         CONSTRAINT fk_dossier_condition FOREIGN KEY (condition_id) REFERENCES condition(condition_id)
);

CREATE TABLE document (
                          document_id SERIAL PRIMARY KEY,
                          code VARCHAR(255) NOT NULL,
                          titre VARCHAR(255) NOT NULL,
                          description VARCHAR(255),
                          est_acte BOOLEAN NOT NULL,
                          dossier_id BIGINT NOT NULL,
                          procedure_id BIGINT NOT NULL,
                          famille_id BIGINT,
                          CONSTRAINT fk_document_dossier FOREIGN KEY (dossier_id) REFERENCES dossier(dossier_id),
                          CONSTRAINT fk_document_procedure FOREIGN KEY (procedure_id) REFERENCES procedure(procedure_id),
                          CONSTRAINT fk_document_famille FOREIGN KEY (famille_id) REFERENCES famille_document(famille_id)
);

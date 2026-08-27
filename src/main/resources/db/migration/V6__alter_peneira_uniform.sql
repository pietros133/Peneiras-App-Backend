ALTER TABLE peneira
DROP COLUMN uniform;

CREATE TABLE peneira_uniform (
                                 peneira_id UUID NOT NULL,
                                 uniform VARCHAR(50) NOT NULL,

                                 CONSTRAINT pk_peneira_uniform
                                     PRIMARY KEY (peneira_id, uniform),

                                 CONSTRAINT fk_peneira_uniform_peneira
                                     FOREIGN KEY (peneira_id)
                                         REFERENCES peneira(id)
);
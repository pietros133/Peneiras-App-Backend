CREATE TABLE endereco (
                          id UUID PRIMARY KEY,
                          rua VARCHAR(255) NOT NULL,
                          bairro VARCHAR(255) NOT NULL,
                          numero VARCHAR(255) NOT NULL,
                          cep VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255) NOT NULL,
                          estado VARCHAR(255) NOT NULL,
                          complemento VARCHAR(255)
);
CREATE TABLE clube (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(72) NOT NULL,
                       category VARCHAR(255) NOT NULL,
                       endereco_id UUID NOT NULL,
                       clube_img VARCHAR(255) NOT NULL,
                       phone VARCHAR(255) NOT NULL,
                       whatsapp VARCHAR(255),
                       instagram_account VARCHAR(255),

                       CONSTRAINT fk_clube_endereco
                           FOREIGN KEY (endereco_id)
                               REFERENCES endereco(id)
);

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(72) NOT NULL,
                       birth_date DATE NOT NULL,
                       position VARCHAR(255) NOT NULL,
                       dominant_foot VARCHAR(255) NOT NULL,
                       height_cm INTEGER NOT NULL,
                       endereco_id UUID NOT NULL,
                       user_img VARCHAR(255),

                       CONSTRAINT fk_user_endereco
                           FOREIGN KEY (endereco_id)
                               REFERENCES endereco(id)
);

CREATE TABLE peneira (
                         id UUID PRIMARY KEY,
                         category VARCHAR(255) NOT NULL,
                         modality VARCHAR(255) NOT NULL,
                         date DATE NOT NULL,
                         hour TIME NOT NULL,
                         uniform VARCHAR(255) NOT NULL,
                         documents VARCHAR(255) NOT NULL,
                         about VARCHAR(255) NOT NULL,
                         clube_id UUID NOT NULL,

                         CONSTRAINT fk_peneira_clube
                             FOREIGN KEY (clube_id)
                                 REFERENCES clube(id)
);
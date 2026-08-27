CREATE TABLE player_peneira (
                                id UUID PRIMARY KEY,
                                player_id UUID NOT NULL,
                                peneira_id UUID NOT NULL,
                                inscrito_em TIMESTAMP NOT NULL,

                                CONSTRAINT fk_player
                                    FOREIGN KEY (player_id)
                                        REFERENCES player(id),

                                CONSTRAINT fk_peneira
                                    FOREIGN KEY (peneira_id)
                                        REFERENCES peneira(id),

                                CONSTRAINT uk_player_peneira
                                    UNIQUE (player_id, peneira_id)
);
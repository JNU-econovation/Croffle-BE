CREATE TABLE music_tb (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          music_url VARCHAR(255) NOT NULL
);

CREATE TABLE title_tb (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          speed INT NOT NULL,
                          mood VARCHAR(255) NOT NULL,
                          loc VARCHAR(255) NOT NULL,
                          music_id BIGINT NOT NULL,
                          CONSTRAINT fk_music
                              FOREIGN KEY (music_id)
                                  REFERENCES music_tb (id)
);
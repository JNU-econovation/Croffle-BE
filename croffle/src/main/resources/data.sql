INSERT INTO music_tb (music_url, title) VALUES ('${s3.url}', '속도가 70%로 잔잔한 오후의 카페에서 느끼는 여유로움1');
INSERT INTO music_tb (music_url, title) VALUES ('${s3.url}', '속도가 70%로 잔잔한 오후의 카페에서 느끼는 여유로움2');
INSERT INTO music_tb (music_url, title) VALUES ('${s3.url}', '속도가 70%로 잔잔한 오후의 카페에서 느끼는 여유로움3');
INSERT INTO music_tb (music_url, title) VALUES ('${s3.url}', '속도가 70%로 잔잔한 오후의 카페에서 느끼는 여유로움4');

INSERT INTO member_tb (google_id, name) VALUES ('1111111111', 'Member1');
INSERT INTO member_tb (google_id, name) VALUES ('2222222222', 'Member2');
INSERT INTO member_tb (google_id, name) VALUES ('${google.id}', 'Minju Kwak');

INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '해변의 여유로운 산책', 1);
INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '고요한 숲속의 아침', 1);
INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '봄날의 산들바람', 2);
INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '달빛 아래의 산책', 2);
INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '가을의 낙엽길', 3);
INSERT INTO mymusic_tb (music_url, title, member_id) VALUES ('${s3.url}', '도시의 야경', 3);

INSERT INTO like_tb (member_id, music_id) VALUES (1, 1);
INSERT INTO like_tb (member_id, music_id) VALUES (1, 2);
INSERT INTO like_tb (member_id, music_id) VALUES (3, 3);
INSERT INTO like_tb (member_id, music_id) VALUES (3, 1);
INSERT INTO like_tb (member_id, music_id) VALUES (3, 3);
INSERT INTO like_tb (member_id, music_id) VALUES (3, 2);


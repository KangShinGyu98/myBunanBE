-- Insert sample data into the 'music' table
-- Insert data into the 'music' table
INSERT INTO music (ID,TITLE, VIDEO_ID, LIKES, VIEWS, COUNTRY, GENRE, SINGER, WRITER, RELEASED, POSTED, MODIFIED, DELETED) VALUES
       (1,'Song 1', 'liJVSwOiiwg', 100,1, '미국 (United States)', 'Pop', 'Singer 1', 'Writer 1', '2023-01-15', '2023-01-01', '2023-01-15', NULL),
       (2,'Song 2', 'liJVSwOiiwg', 150,2,'영국 (United Kingdom)', 'Pop', 'Singer 2', 'Writer 2', '2023-02-20', '2023-02-01', '2023-02-20', NULL),
       (3,'Song 4', 'liJVSwOiiwg', 15,10, '영국 (United Kingdom)', 'Rock', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (4,'Song 5', 'liJVSwOiiwg', 200,1000000, '브라질 (Brazil)', 'Rock', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (5,'Song 6', 'liJVSwOiiwg', 0,5000000, '재팬 (Japan)', 'R&B (Rhythm and Blues)', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (6,'Song 7', 'liJVSwOiiwg', 2,3, '잉글랜드 (Jamaica)', 'R&B (Rhythm and Blues)', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (7,'Song 8', 'liJVSwOiiwg', 3,5, '프랑스 (France)', 'R&B (Rhythm and Blues)', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (8,'Song 9', 'liJVSwOiiwg', 1,2, '스페인 (Spain)', 'R&B (Rhythm and Blues)', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (9,'Song 10', 'liJVSwOiiwg', 2300,500, '남아프리카 (South Africa)', 'R&B (Rhythm and Blues)', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL);

-- Insert sample data into the 'tag' table with references to 'music'
INSERT INTO tag (ID, NAME, MUSIC_ID) VALUES
                     (1, '차분한', 1), -- Tag A is associated with Song 1
                     (2, '사랑스러운', 1), -- Tag B is associated with Song 1
                     (3, '청춘', 2), -- Tag B is associated with Song 2
                     (4, '맑은 목소리', 2), -- Tag C is associated with Song 2
                     (5, '섹시', 3); -- Tag A is associated with Song 3
INSERT INTO lyric ( CONTENT, ORDER_NUMBER, MUSIC_ID) VALUES
        ( 'Lyric 1', 1, 1),
        ( 'Lyric 2', 2, 1),
        ( 'Lyric 3', 1, 2);

-- Insert sample data into the 'lyricComment' table
INSERT INTO lyric_Comment ( CONTENT, LIKES, DISLIKES, REPORTS, WRITER, CREATED, MODIFIED, DELETED, LYRIC_ID) VALUES
       ( 'Comment 1', 10, 2, 1, 'User 1', '2023-01-16', '2023-01-16', NULL, 1),
       ( 'Comment 2', 500, 1, 0, 'User 2', '2023-02-21', '2023-02-21', NULL, 1),
       ( 'Comment 3', 8, 3, 0, 'User 3', '2023-03-11', '2023-03-11', NULL, 2);
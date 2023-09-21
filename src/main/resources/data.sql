-- Insert sample data into the 'music' table
-- Insert data into the 'music' table
INSERT INTO music (ID,NAME, VIDEO_ID, LIKES, VIEWS, COUNTRY, GENRE, SINGER, WRITER, RELEASED, POSTED, MODIFIED, DELETED) VALUES
       (1,'Song 1', 'liJVSwOiiwg', 100,1, 'Country A', 'Genre 1', 'Singer 1', 'Writer 1', '2023-01-15', '2023-01-01', '2023-01-15', NULL),
       (2,'Song 2', 'liJVSwOiiwg', 150,2,'Country B', 'Genre 2', 'Singer 2', 'Writer 2', '2023-02-20', '2023-02-01', '2023-02-20', NULL),
       (3,'Song 4', 'liJVSwOiiwg', 15,10, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (4,'Song 5', 'liJVSwOiiwg', 200,1000000, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (5,'Song 6', 'liJVSwOiiwg', 0,5000000, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (6,'Song 7', 'liJVSwOiiwg', 2,3, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (7,'Song 8', 'liJVSwOiiwg', 3,5, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (8,'Song 9', 'liJVSwOiiwg', 1,2, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL),
       (9,'Song 10', 'liJVSwOiiwg', 2300,500, 'Country A', 'Genre 1', 'Singer 1', 'Writer 3', '2023-03-10', '2023-03-01', '2023-03-10', NULL);

-- Insert sample data into the 'tag' table with references to 'music'
INSERT INTO tag (ID, NAME, MUSIC_ID) VALUES
                     (1, '차분한', 1), -- Tag A is associated with Song 1
                     (2, '사랑스러운', 1), -- Tag B is associated with Song 1
                     (3, '청춘', 2), -- Tag B is associated with Song 2
                     (4, '맑은 목소리', 2), -- Tag C is associated with Song 2
                     (5, '섹시', 3); -- Tag A is associated with Song 3


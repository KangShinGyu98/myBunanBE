-- Create the 'music' table
CREATE TABLE music (
    ID                  INT AUTO_INCREMENT PRIMARY KEY,
    TITLE               VARCHAR(255) NOT NULL,
    VIDEO_ID            VARCHAR(255),
    LIKES               INT DEFAULT 0,
    VIEWS               INT DEFAULT 0,
    COUNTRY             VARCHAR(255),
    GENRE               VARCHAR(255),
    SINGER              VARCHAR(255),
    SONGWRITER          VARCHAR(255),
    POSTWRITER          VARCHAR(255),
    RELEASED            DATE,
    POSTED              DATE,
    MODIFIED            DATE,
    DELETED             DATE,
    FOREIGN KEY (USER_ID) REFERENCES user(ID)
    );

-- Create the 'tag' table with a foreign key reference to 'music'
CREATE TABLE tag (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    MUSIC_ID INT,
    FOREIGN KEY (MUSIC_ID) REFERENCES music(ID)
    );
CREATE TABLE lyric (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(255) NOT NULL,
    ORDER_NUMBER INT,
    MUSIC_ID INT,
    FOREIGN KEY (MUSIC_ID) REFERENCES music(ID)
    FOREIGN KEY (USER_ID) REFERENCES user(ID)

);
CREATE TABLE lyric_comment (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(255) NOT NULL,
    LIKES INT,
    DISLIKES INT,
    REPORTS INT,
    WRITER VARCHAR(255),
    CREATED DATE,
    MODIFIED DATE,
    DELETED DATE,
    LYRIC_ID INT,
    FOREIGN KEY (LYRIC_ID) REFERENCES lyric(ID)
    FOREIGN KEY (USER_ID) REFERENCES user(ID)
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);
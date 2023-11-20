-- Create the 'music' table

CREATE TABLE member (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nickname VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255)
);

CREATE TABLE music (
    ID                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE               VARCHAR(255) NOT NULL,
    VIDEO_ID            VARCHAR(255),
    LIKES               INT DEFAULT 0,
    VIEWS               INT DEFAULT 0,
    COUNTRY             VARCHAR(255),
    GENRE               VARCHAR(255),
    SINGER              VARCHAR(255),
    SONG_WRITER          VARCHAR(255),
    POST_WRITER          VARCHAR(255),
    LYRIC_WRITER          VARCHAR(255),
    REMIX_ARTIST          VARCHAR(255),
    RELEASED            DATE,
    POSTED              DATE,
    MODIFIED            DATE,
    DELETED             DATE,
    MEMBER_ID             BIGINT,
    FOREIGN KEY (MEMBER_ID) REFERENCES member(ID)
    );

-- Create the 'tag' table with a foreign key reference to 'music'
CREATE TABLE tag (
    Id BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    MUSIC_ID INT,
    FOREIGN KEY (MUSIC_ID) REFERENCES music(ID)
    );
CREATE TABLE lyric (
    Id BIGINT AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(255) NOT NULL,
    ORDER_NUMBER INT,
    MUSIC_ID INT,
    FOREIGN KEY (MUSIC_ID) REFERENCES music(ID) ON DELETE CASCADE,
    MEMBER_ID BIGINT,
    FOREIGN KEY (MEMBER_ID) REFERENCES member(ID)

);
CREATE TABLE lyric_comment (
    Id BIGINT AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(255) NOT NULL,
    LIKES INT,
    DISLIKES INT,
    REPORTS INT,
    WRITER VARCHAR(255),
    CREATED DATE,
    MODIFIED DATE,
    DELETED DATE,
    LYRIC_ID INT,
    FOREIGN KEY (LYRIC_ID) REFERENCES lyric(ID) ON DELETE CASCADE,
    MEMBER_ID BIGINT,
    FOREIGN KEY (MEMBER_ID) REFERENCES member(ID)
);

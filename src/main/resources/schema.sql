-- Create the 'music' table
CREATE TABLE music (
    ID                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE               VARCHAR(255) NOT NULL,
    VIDEO_ID            VARCHAR(255),
    LIKES               INT DEFAULT 0,
    VIEWS               INT DEFAULT 0,
    COUNTRY             VARCHAR(255),
    GENRE               VARCHAR(255),
    SINGER              VARCHAR(255),
    WRITER              VARCHAR(255),
    RELEASED            DATE,
    POSTED              DATE,
    MODIFIED            DATE,
    DELETED             DATE
--     USER_ID             INT REFERENCES user(ID)
    );

-- Create the 'tag' table with a foreign key reference to 'music'
CREATE TABLE tag (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    MUSIC_ID BIGINT,
    FOREIGN KEY (MUSIC_ID) REFERENCES music(ID)
    );

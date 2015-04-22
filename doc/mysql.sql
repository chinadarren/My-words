create DATABASE words;
use words;

# user table
CREATE TABLE user (
  id  int(11) AUTO_INCREMENT PRIMARY KEY ,
  username  VARCHAR(255),
  password  VARCHAR(255)
);

SELECT * FROM user;

# words table
CREATE TABLE words (
  id  INT(11) AUTO_INCREMENT PRIMARY KEY ,
  english VARCHAR(255),
  property VARCHAR(255),
  chinese VARCHAR(255),
  level VARCHAR(255)
);

ALTER TABLE words
    RENAME TO word;

SELECT * FROM word;

TRUNCATE TABLE word;

select * from word WHERE english like '%let%' OR chinese like '%let%'
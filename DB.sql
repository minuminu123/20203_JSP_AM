# DB 생성
DROP DATABASE IF EXISTS `JSPAM`;
CREATE DATABASE `JSPAM`;
USE `JSPAM`;

# article 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# member 테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL
);

# article 테스트데이터 생성
INSERT INTO article
SET regDate = NOW(),
title = '제목 1',
`body` = '내용 1';

INSERT INTO article
SET regDate = NOW(),
title = '제목 2',
`body` = '내용 2';

INSERT INTO article
SET regDate = NOW(),
title = '제목 3',
`body` = '내용 3';

INSERT INTO article
SET regDate = NOW(),
title = '제목 4',
`body` = '내용 4';

INSERT INTO article
SET regDate = NOW(),
title = '제목 5',
`body` = '내용 5';

# member 테스트데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = '회원3';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;

UPDATE article 
SET memberId = 2
WHERE id IN (1,2);

UPDATE article 
SET memberId = 1
WHERE id IN (3,4);

UPDATE article 
SET memberId = 3
WHERE id = 5;


#################################

DESC article;

INSERT INTO article
SET regDate = NOW(),
title = CONCAT('제목__', RAND()),
`body` = CONCAT('내용__', RAND());


SELECT * FROM article;

SELECT * FROM `member`;
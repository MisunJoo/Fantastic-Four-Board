<<<<<<< Updated upstream
=======
# DROP TABLE
>>>>>>> Stashed changes
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `permission`;
DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `member_permission`;
DROP TABLE IF EXISTS `article`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `article_counting`;
DROP TABLE IF EXISTS `article_content`;
SET FOREIGN_KEY_CHECKS = 1;

<<<<<<< Updated upstream
=======
#CREATE TABLE
>>>>>>> Stashed changes
CREATE TABLE `category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `permission` (
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `member` (
  `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nick_name` VARCHAR(20) NOT NULL UNIQUE KEY,
  `password` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL UNIQUE KEY,
  PRIMARY KEY (`id`)
);

CREATE TABLE `member_permission` (
  `member_id` BIGINT(10) UNSIGNED NOT NULL,
  `perm_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `article` (
  `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `hit` INT UNSIGNED NOT NULL DEFAULT 0,
  `nick_name` VARCHAR(20) NOT NULL,
  `group_id` BIGINT(10) UNSIGNED,
  `depth_level` INT UNSIGNED NOT NULL DEFAULT 0,
  `group_seq` INT UNSIGNED NOT NULL DEFAULT 0,
  `regdate` DATETIME NOT NULL,
  `upddate` DATETIME,
  `category_id` INT UNSIGNED NOT NULL,
  `ip_address` VARCHAR(20) NOT NULL,
  `member_id` BIGINT(10) UNSIGNED NOT NULL,
  `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `comment` (
  `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `articleId` BIGINT(10) UNSIGNED NOT NULL,
  `nick_name` VARCHAR(20) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `group_id` BIGINT(10) UNSIGNED,
  `depth_level` INT UNSIGNED NOT NULL DEFAULT 0,
  `group_seq` INT UNSIGNED NOT NULL DEFAULT 0,
  `regdate` DATETIME NOT NULL,
  `upddate` DATETIME,
  `ip_address` VARCHAR(20) NOT NULL,
  `member_id` BIGINT(10) UNSIGNED NOT NULL,
  `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `article_counting` (
  `category_id` INT UNSIGNED NOT NULL,
  `count` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`category_id`)
);

CREATE TABLE `article_content` (
  `articleId` BIGINT(10) UNSIGNED NOT NULL,
  `content` TEXT NOT NULL,
  PRIMARY KEY (`articleId`)
);

ALTER TABLE `member_permission` ADD FOREIGN KEY (`perm_name`) REFERENCES `permission`(`name`);
ALTER TABLE `member_permission` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `article` ADD FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `article` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`articleId`) REFERENCES `article`(`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `article_counting` ADD FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `article_content` ADD FOREIGN KEY (`articleId`) REFERENCES `article`(`id`);

<<<<<<< Updated upstream
=======
# permission에 권한 행 추가
>>>>>>> Stashed changes
INSERT INTO permission(name) VALUES ('read');
INSERT INTO permission(name) VALUES ('write');
INSERT INTO permission(name) VALUES ('update');
INSERT INTO permission(name) VALUES ('delete');
INSERT INTO permission(name) VALUES ('admin_delete');
SELECT * FROM permission;

<<<<<<< Updated upstream
INSERT INTO member(nick_name,password,email) VALUES ('admin','1234','webmaster@naver.com');
SELECT * FROM member;

=======
# member에 admin추가
INSERT INTO member(nick_name,password,email) VALUES ('admin','1234','webmaster@naver.com');
SELECT * FROM member;

# admin에게 모든 permission 추가
>>>>>>> Stashed changes
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'read');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'write');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'update');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'delete');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'admin_delete');
SELECT * FROM member_permission;

<<<<<<< Updated upstream
=======
# category 2개 추가
>>>>>>> Stashed changes
INSERT INTO category(name) VALUES ('자바');
INSERT INTO category(name) VALUES ('스프링프레임워크');
SELECT * FROM category;

<<<<<<< Updated upstream
=======
# 관리자가 자바게시판에 글을 쓴다.// 기본정보 삽입 -> group_id 삽입 -> article_content삽입
>>>>>>> Stashed changes
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id)
VALUES ('자바 게시판에선 자바이야기만 해주세요.','관리자', 0, 0, now(),1,'192.168.0.4',1);
UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID());
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'스프링 게시판은 따로 있습니다.');
SELECT * FROM article WHERE category_id=1;
SELECT * FROM article_content;

<<<<<<< Updated upstream
=======
# 관리자가 스프링게시판에 글을 쓴다.// 기본정보 삽입 -> group_id 삽입 -> article_content삽입
>>>>>>> Stashed changes
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id)
VALUES ('스프링 게시판에선 스프링 이야기만 해주세요.','관리자', 0, 0, now(),2,'192.168.0.4',1);
UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID());
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'자바 게시판은 따로 있습니다.');
SELECT * FROM article WHERE category_id=2;
SELECT * FROM article_content;

<<<<<<< Updated upstream
=======
# member 홍길동 회원가입, 회원권한(읽기,쓰기,수정,삭제) 부여
>>>>>>> Stashed changes
INSERT INTO member(nick_name,password,email) VALUES ('홍길동','hong1234','gildong@naver.com');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'read');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'write');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'update');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'delete');
SELECT * FROM member;

<<<<<<< Updated upstream
=======
# 홍길동이 자바게시판에 글을 쓴다. // 기본정보 삽입 -> group_id 삽입 -> article_content삽입
>>>>>>> Stashed changes
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id)
VALUES ('자바 배우기 어렵나요?','홍길동', 0, 0, now(),1,'192.168.0.4',2);
UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID());
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'제가 개발을 하고 싶은데 하나도 몰라요 파이썬할까요?');
SELECT * FROM article;
SELECT * FROM article_content;

<<<<<<< Updated upstream
=======
# member 유어스토리 회원가입, 회원권한(읽기,쓰기,수정,삭제) 부여
>>>>>>> Stashed changes
INSERT INTO member(nick_name,password,email) VALUES ('유어스토리','ur1234','urstory@naver.com');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'read');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'write');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'update');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'delete');
SELECT * FROM member;

<<<<<<< Updated upstream
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

=======
# member 유어스토리가 자바게시판의 목록을 불러온다.
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# member 유어스토리가 보고싶은 게시물을 클릭하여서 불러온다. id=3 // 글 기본정보, 제목, 댓글
>>>>>>> Stashed changes
SELECT * FROM article WHERE id=3;
SELECT content FROM article_content WHERE articleId=3;
SELECT * FROM comment WHERE articleId=3;

<<<<<<< Updated upstream
=======
# member 유어스토리가 '자바 배우기 어렵나요' id=3 의 답변을 씀
# 현재 글의 depth_level, group_seq, group_id는 이미 알고 있기 떄문에 같이 넣어준다.
>>>>>>> Stashed changes
UPDATE article SET group_seq = group_seq + 1 WHERE group_id = 3 AND group_seq >= 0+1;
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id, group_id)
VALUES ('객체지향에 대해서 먼저 이해하신다면','유어스토리', 0+1, 0+1, now(),1,'192.168.0.4',3,3);
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'배우기가 훨씬 수월할겁니다.');
SELECT * FROM article;
SELECT * FROM article_content;

<<<<<<< Updated upstream
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

=======
# member 유어스토리가 자바게시판의 목록을 불러온다.
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# member 남궁성 회원가입, 회원권한(읽기,쓰기,수정,삭제) 부여
>>>>>>> Stashed changes
INSERT INTO member(nick_name,password,email) VALUES ('남궁성','nam1234','nam-gung@naver.com');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'read');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'write');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'update');
INSERT INTO member_permission(member_id, perm_name) VALUES ((SELECT LAST_INSERT_ID()),'delete');
SELECT * FROM member;

<<<<<<< Updated upstream
=======
# member 남궁성이 '자바 배우기 어렵나요' id=3 의 답변을 씀
# 현재 글의 depth_level, group_seq, group_id는 이미 알고 있기 떄문에 같이 넣어준다.
>>>>>>> Stashed changes
UPDATE article SET group_seq = group_seq + 1 WHERE group_id = 3 AND group_seq >= 0+1;
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id, group_id)
VALUES ('자바 입문서를 고민하신다면 자바의정석 보세요.','남궁성', 0+1, 0+1, now(),1,'192.168.0.4',4,3);
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'사실 제가 저자입니다 ㅎㅎ');
SELECT * FROM article;
SELECT * FROM article_content;

<<<<<<< Updated upstream
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

=======
# member 유어스토리가 자바게시판의 목록을 불러온다.
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# member 유어스토리가 '자바 입문서를 고민하신다면 자바의정석 보세요.' id=5 의 답변을 씀
# 현재 글의 depth_level, group_seq, group_id는 이미 알고 있기 떄문에 같이 넣어준다.
>>>>>>> Stashed changes
UPDATE article SET group_seq = group_seq + 1 WHERE group_id = 3 AND group_seq >= 1+1;
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id, group_id)
VALUES ('지바의정석보다는 자바의신 더 좋지 않나요?','유어스토리', 1+1, 1+1, now(),1,'192.168.0.4',3,3);
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'사실 제가 검수함ㅎㅎ');
SELECT * FROM article;
SELECT * FROM article_content;

<<<<<<< Updated upstream
=======
# 관리자가 자바게시판에 글을 쓴다.
>>>>>>> Stashed changes
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id)
VALUES ('저자들의 책 홍보는 앞으로 금지됩니다.','관리자', 0, 0, now(),1,'192.168.0.4',1);
UPDATE article SET group_id=(SELECT LAST_INSERT_ID()) WHERE id=(SELECT LAST_INSERT_ID());
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'검수자도 마찬가지에요.');

<<<<<<< Updated upstream
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

=======
# 게시판 목록 확인
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# member 남궁성이 '저자들의 책 홍보는 앞으로 금지됩니다.' id=7 의 답변을 씀
>>>>>>> Stashed changes
UPDATE article SET group_seq = group_seq + 1 WHERE group_id = 7 AND group_seq >= 0+1;
INSERT INTO article(title, nick_name, depth_level, group_seq, regdate, category_id,ip_address,member_id, group_id)
VALUES ('앞으로 자재 하겠읍니다.','남궁성', 0+1, 0+1, now(),1,'192.168.0.4',4,7);
INSERT INTO article_content(articleId, content) VALUES ((SELECT LAST_INSERT_ID()),'그래도 자바의 정석 좋아요');
SELECT * FROM article;
SELECT * FROM article_content;

<<<<<<< Updated upstream
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 5 , 5;


UPDATE article SET title='앞으로 자제 하겠습니다.' WHERE id=8;

SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

=======
# 게시판 목록 확인
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# 게시판 목록 2페이지 확인
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 5 , 5;


# member 남궁성이 본인의 글을 수정함
# ?근데 권한 검사는 언제하지? SQL로 바로 하는걸까?
UPDATE article SET title='앞으로 자제 하겠습니다.' WHERE id=8;

# 게시판 목록 확인
SELECT * FROM article WHERE category_id=1 ORDER BY group_id DESC, group_seq ASC LIMIT 0 , 5;

# member 남궁성이 본인의 글을 삭제함
>>>>>>> Stashed changes
UPDATE article SET is_deleted=TRUE WHERE id=5;
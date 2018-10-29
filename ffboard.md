# CREATE Fantastic Four board



## create database

```mysql
create database ffboard;
use ffboard;
```



## create table

* 회원 테이블(member)

```mysql
CREATE TABLE member (
	member_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) UNIQUE KEY,
	password VARCHAR(20),
	email VARCHAR(50),
	auth_id INT(5),
	PRIMARY KEY(member_id));
```



* 아티클

```mysql
CREATE TABLE article (
	articleId BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
    hit int(10) DEFAULT 0,
    name VARCHAR(20) NOT NULL,
    group_id BIGINT NOT NULL, 
    depth_level INT(5),
    regdate DATE,
    category_id INT(10) NOT NULL,
    PRIMARY KEY(articleId)
	);
```



* 댓글 

```mysql
CREATE TABLE comment (
	comment_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	articleId BIGINT NOT NULL,
	name VARCHAR(20) not null,
	content VARCHAR(255),
	group_id BIGINT NOT NULL,
	regdate DATE,
	PRIMARY KEY(comment_id));
```



* 권한

```mysql
CREATE TABLE auth (
)
```






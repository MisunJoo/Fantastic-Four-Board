# DROP TABLE
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

#CREATE TABLE
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
  `update` DATETIME,
  `category_id` INT UNSIGNED NOT NULL,
  `ip_address` VARCHAR(20) NOT NULL,
  `member_id` BIGINT(10) UNSIGNED NOT NULL,
  `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `comment` (
  `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT(10) UNSIGNED NOT NULL,
  `nick_name` VARCHAR(20) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `group_id` BIGINT(10) UNSIGNED,
  `depth_level` INT UNSIGNED NOT NULL DEFAULT 0,
  `group_seq` INT UNSIGNED NOT NULL DEFAULT 0,
  `regdate` DATETIME NOT NULL,
  `update` DATETIME,
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
  `article_id` BIGINT(10) UNSIGNED NOT NULL,
  `content` TEXT NOT NULL,
  PRIMARY KEY (`article_id`)
);

ALTER TABLE `member_permission` ADD FOREIGN KEY (`perm_name`) REFERENCES `permission`(`name`);
ALTER TABLE `member_permission` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `article` ADD FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `article` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`article_id`) REFERENCES `article`(`id`);
ALTER TABLE `comment` ADD FOREIGN KEY (`member_id`) REFERENCES `member`(`id`);
ALTER TABLE `article_counting` ADD FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `article_content` ADD FOREIGN KEY (`article_id`) REFERENCES `article`(`id`);
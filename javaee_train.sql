# Host: localhost  (Version 5.6.15)
# Date: 2018-09-21 11:11:37
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `mailbox` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员';

#
# Structure for table "paper"
#

DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL COMMENT '试卷科目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷';

#
# Structure for table "examination"
#

DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `date` date DEFAULT NULL COMMENT '考试时间',
  `paper_id` int(11) DEFAULT NULL COMMENT '试卷编号',
  PRIMARY KEY (`id`),
  KEY `paper_id` (`paper_id`),
  CONSTRAINT `examination_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试';

#
# Structure for table "admin_examination"
#

DROP TABLE IF EXISTS `admin_examination`;
CREATE TABLE `admin_examination` (
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '管理员用户名',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '考试编号',
  PRIMARY KEY (`username`,`id`),
  KEY `Id` (`id`),
  CONSTRAINT `admin_examination_ibfk_1` FOREIGN KEY (`username`) REFERENCES `admin` (`username`),
  CONSTRAINT `admin_examination_ibfk_2` FOREIGN KEY (`Id`) REFERENCES `examination` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员管理考试信息';

#
# Structure for table "admin_paper"
#

DROP TABLE IF EXISTS `admin_paper`;
CREATE TABLE `admin_paper` (
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '管理员用户名',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '试卷编号',
  PRIMARY KEY (`username`,`id`),
  KEY `Id` (`id`),
  CONSTRAINT `admin_paper_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `paper` (`Id`),
  CONSTRAINT `admin_paper_ibfk_2` FOREIGN KEY (`username`) REFERENCES `admin` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员管理试卷';

#
# Structure for table "question"
#

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind` bit(1) DEFAULT NULL COMMENT '是客观题还是主观题',
  `subject` varchar(255) DEFAULT NULL COMMENT '科目',
  `timian` varchar(255) DEFAULT NULL COMMENT '题面',
  `answer` bit(2) DEFAULT NULL COMMENT '答案（0,1,2,3）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试题目';

#
# Structure for table "paper_question"
#

DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question` (
  `paper_id` int(11) NOT NULL DEFAULT '0',
  `question_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`paper_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `paper_question_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`Id`),
  CONSTRAINT `paper_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷采用的题目';

#
# Structure for table "admin_question"
#

DROP TABLE IF EXISTS `admin_question`;
CREATE TABLE `admin_question` (
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '管理员用户名',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '题目编号',
  PRIMARY KEY (`username`,`id`),
  KEY `Id` (`id`),
  CONSTRAINT `admin_question_ibfk_1` FOREIGN KEY (`username`) REFERENCES `admin` (`username`),
  CONSTRAINT `admin_question_ibfk_2` FOREIGN KEY (`Id`) REFERENCES `question` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员管理题库';

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `mailbox` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通用户';

#
# Structure for table "user_admin"
#

DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `admin_username` varchar(255) NOT NULL DEFAULT '' COMMENT '管理员用户名',
  `user_username` varchar(255) NOT NULL DEFAULT '' COMMENT '普通用户用户名',
  PRIMARY KEY (`admin_username`,`user_username`),
  KEY `user_username` (`user_username`),
  CONSTRAINT `user_admin_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `admin` (`username`),
  CONSTRAINT `user_admin_ibfk_2` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订阅管理员';

#
# Structure for table "user_examination"
#

DROP TABLE IF EXISTS `user_examination`;
CREATE TABLE `user_examination` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '考试编号',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '普通用户用户名',
  `grade` int(11) DEFAULT NULL COMMENT '分数',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  PRIMARY KEY (`id`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `user_examination_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `examination` (`Id`),
  CONSTRAINT `user_examination_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户参加考试';

#
# Structure for table "user_paper"
#

DROP TABLE IF EXISTS `user_paper`;
CREATE TABLE `user_paper` (
  `id` int(11) NOT NULL DEFAULT '0',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '普通用户用户名',
  PRIMARY KEY (`id`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `user_paper_ibfk_1` FOREIGN KEY (`id`) REFERENCES `paper` (`Id`),
  CONSTRAINT `user_paper_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏试卷';

#
# Structure for table "user_question"
#

DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `id` int(11) NOT NULL DEFAULT '0',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '普通用户用户名',
  PRIMARY KEY (`id`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `user_question_ibfk_1` FOREIGN KEY (`id`) REFERENCES `question` (`Id`),
  CONSTRAINT `user_question_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏题目';

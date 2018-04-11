-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.7-rc-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ap
CREATE DATABASE IF NOT EXISTS `ap` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ap`;

-- Dumping structure for table ap.tblanswertypelookup
CREATE TABLE IF NOT EXISTS `tblanswertypelookup` (
  `answer_type_id` int(11) NOT NULL,
  `answer_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`answer_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblanswertypelookup: ~2 rows (approximately)
/*!40000 ALTER TABLE `tblanswertypelookup` DISABLE KEYS */;
INSERT INTO `tblanswertypelookup` (`answer_type_id`, `answer_type`) VALUES
	(1, 'Radio'),
	(2, 'Checkbox');
/*!40000 ALTER TABLE `tblanswertypelookup` ENABLE KEYS */;

-- Dumping structure for table ap.tblattempt
CREATE TABLE IF NOT EXISTS `tblattempt` (
  `category_id` int(11) NOT NULL,
  `no_attempt` int(2) NOT NULL,
  `no_question` int(3) NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`category_id`,`no_attempt`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `tblcategory` (`category_id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblattempt: ~8 rows (approximately)
/*!40000 ALTER TABLE `tblattempt` DISABLE KEYS */;
INSERT INTO `tblattempt` (`category_id`, `no_attempt`, `no_question`, `time`) VALUES
	(13, 1, 10, '00:15:00'),
	(13, 2, 5, '00:15:00'),
	(13, 3, 5, '00:15:00'),
	(13, 4, 5, '00:15:00'),
	(13, 5, 25, '00:01:00'),
	(14, 1, 5, '00:30:00'),
	(14, 2, 5, '00:30:00'),
	(14, 3, 5, '00:30:00');
/*!40000 ALTER TABLE `tblattempt` ENABLE KEYS */;

-- Dumping structure for table ap.tblcategory
CREATE TABLE IF NOT EXISTS `tblcategory` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `cut_off` int(11) DEFAULT NULL,
  `max_attempt` int(2) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblcategory: ~4 rows (approximately)
/*!40000 ALTER TABLE `tblcategory` DISABLE KEYS */;
INSERT INTO `tblcategory` (`category_id`, `category_name`, `description`, `cut_off`, `max_attempt`) VALUES
	(13, 'Core Java', 'core java Assesment', 50, 5),
	(14, 'HealthCare', 'Fundamentals of healthcare Assesment', 80, 5),
	(20, 'Test 23', 'asda sd', 12, 1),
	(23, 'OOPs', 'OOPs concept', 80, 3);
/*!40000 ALTER TABLE `tblcategory` ENABLE KEYS */;

-- Dumping structure for table ap.tblcomplexity
CREATE TABLE IF NOT EXISTS `tblcomplexity` (
  `complexity` int(3) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`complexity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Defines the complexity of a question (1 being the LEAST and ''n'' being MOST complex)';

-- Dumping data for table ap.tblcomplexity: ~3 rows (approximately)
/*!40000 ALTER TABLE `tblcomplexity` DISABLE KEYS */;
INSERT INTO `tblcomplexity` (`complexity`, `description`) VALUES
	(1, 'Simple'),
	(2, 'Medium'),
	(3, 'Complex');
/*!40000 ALTER TABLE `tblcomplexity` ENABLE KEYS */;

-- Dumping structure for table ap.tblcomplexitylookup
CREATE TABLE IF NOT EXISTS `tblcomplexitylookup` (
  `category_id` int(11) NOT NULL,
  `complexity` int(3) NOT NULL,
  `no_attempt` int(2) NOT NULL,
  `percentage` int(3) NOT NULL,
  KEY `FK_tblcomplexitylookup_tblcategory` (`category_id`),
  KEY `FK_tblcomplexitylookup_tblcomplexity` (`complexity`),
  CONSTRAINT `FK_tblcomplexitylookup_tblcategory` FOREIGN KEY (`category_id`) REFERENCES `tblcategory` (`category_id`),
  CONSTRAINT `FK_tblcomplexitylookup_tblcomplexity` FOREIGN KEY (`complexity`) REFERENCES `tblcomplexity` (`complexity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblcomplexitylookup: ~22 rows (approximately)
/*!40000 ALTER TABLE `tblcomplexitylookup` DISABLE KEYS */;
INSERT INTO `tblcomplexitylookup` (`category_id`, `complexity`, `no_attempt`, `percentage`) VALUES
	(13, 1, 1, 75),
	(13, 2, 1, 25),
	(13, 3, 1, 0),
	(13, 1, 2, 50),
	(13, 2, 2, 25),
	(13, 3, 2, 25),
	(13, 1, 3, 25),
	(13, 2, 3, 25),
	(13, 3, 3, 50),
	(14, 1, 1, 50),
	(14, 2, 1, 50),
	(14, 3, 1, 0),
	(14, 1, 2, 25),
	(14, 2, 2, 50),
	(14, 3, 2, 25),
	(14, 1, 3, 0),
	(14, 2, 3, 50),
	(14, 3, 3, 50),
	(13, 1, 4, 25),
	(13, 2, 4, 25),
	(13, 3, 4, 50),
	(13, 1, 5, 25),
	(13, 2, 5, 25),
	(13, 3, 5, 50);
/*!40000 ALTER TABLE `tblcomplexitylookup` ENABLE KEYS */;

-- Dumping structure for table ap.tbloptions
CREATE TABLE IF NOT EXISTS `tbloptions` (
  `question_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `option_desc` varchar(500) DEFAULT NULL,
  `correct_answer` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`option_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `question_id` FOREIGN KEY (`question_id`) REFERENCES `tblquestion` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tbloptions: ~39 rows (approximately)
/*!40000 ALTER TABLE `tbloptions` DISABLE KEYS */;
INSERT INTO `tbloptions` (`question_id`, `option_id`, `option_desc`, `correct_answer`) VALUES
	(1, 1, 'Inheritance', 'Yes'),
	(1, 3, 'Interface', 'No'),
	(2, 5, 'Yes', 'No'),
	(2, 6, 'No', 'Yes'),
	(3, 7, 'Checked exceptions are checked at compile-time.', 'Yes'),
	(3, 8, 'Unchecked exceptions are not checked at compile-time.', 'Yes'),
	(3, 9, 'Both extend Throwable', 'No'),
	(3, 10, 'Both extend RuntimeException ', 'No'),
	(4, 11, 'Runnable', 'Yes'),
	(4, 12, 'Throwable', 'No'),
	(4, 13, 'Serializable', 'No'),
	(4, 14, 'Cloneable', 'No'),
	(5, 15, 'Health Insurance Portability and Accountability Act', 'Yes'),
	(5, 16, 'Health Insurance Protected and Accountability Act', 'No'),
	(5, 17, 'HealthCare Insurance Portability and Accountability Act', 'No'),
	(5, 18, 'None of the above', 'No'),
	(6, 19, 'Private Health Information', 'No'),
	(6, 20, 'Protected Health Information', 'Yes'),
	(6, 21, 'Personal Health Information', 'No'),
	(6, 22, 'Protected Healthcare Information', 'No'),
	(7, 23, 'Business Associate Rule', 'No'),
	(7, 24, 'Encryption Rule', 'No'),
	(7, 25, 'Secure coding', 'No'),
	(7, 26, 'Minimum Necessary Rule', 'Yes'),
	(8, 27, 'Deductible', 'Yes'),
	(8, 28, 'Co-insurance', 'No'),
	(8, 29, 'Co-pay', 'No'),
	(8, 30, 'Premium', 'No'),
	(9, 31, 'Medication reimbursement only', 'No'),
	(9, 32, 'Comprehensive health insurance', 'Yes'),
	(9, 33, 'Physician Fee only', 'No'),
	(9, 34, 'Lab diagnostic reimbursement only', 'No'),
	(10, 35, 'Ambulatory care', 'No'),
	(10, 36, 'Home Healthcare', 'Yes'),
	(10, 37, 'Acute Care', 'No'),
	(10, 38, 'Urgent care', 'No'),
	(12, 39, '20', 'No'),
	(12, 40, '11', 'No'),
	(12, 41, '15', 'Yes'),
	(22, 51, 'True', 'Yes'),
	(22, 52, 'False', 'No');
/*!40000 ALTER TABLE `tbloptions` ENABLE KEYS */;

-- Dumping structure for table ap.tblquestion
CREATE TABLE IF NOT EXISTS `tblquestion` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_desc` varchar(500) DEFAULT NULL,
  `answer_type_id` int(11) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `complexity` int(3) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `category_id` (`category_id`),
  KEY `answer_type_id` (`answer_type_id`),
  KEY `complexity` (`complexity`),
  CONSTRAINT `tblquestion_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `tblcategory` (`category_id`),
  CONSTRAINT `tblquestion_ibfk_2` FOREIGN KEY (`answer_type_id`) REFERENCES `tblanswertypelookup` (`answer_type_id`),
  CONSTRAINT `tblquestion_ibfk_3` FOREIGN KEY (`complexity`) REFERENCES `tblcomplexity` (`complexity`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblquestion: ~16 rows (approximately)
/*!40000 ALTER TABLE `tblquestion` DISABLE KEYS */;
INSERT INTO `tblquestion` (`question_id`, `question_desc`, `answer_type_id`, `category_id`, `complexity`) VALUES
	(1, 'Select below oops concepts', 2, 13, 1),
	(2, 'Can we override static method?', 1, 13, 2),
	(3, 'What is difference between Checked Exception and Unchecked Exception?', 2, 13, 2),
	(4, 'Which of the following is an functional interface ?', 1, 13, 3),
	(5, 'HIPPA Stands for', 1, 14, 1),
	(6, 'PHI stand for', 1, 14, 1),
	(7, 'The _____ States that the lease amount of PHI should be used to complete the task/function', 1, 14, 2),
	(8, '____ is a fixed Dollar amount that a member or insurer must pay for healthcare services, within the year, before insurance starts to pay for his benefits.', 1, 14, 3),
	(9, 'What does Patient Protection and Affordability Care Act cover?', 1, 14, 3),
	(10, 'Which of the following option provide healthcare services to patients from their home?', 1, 14, 3),
	(12, 'How many team members are there in team 20?', 1, 13, 2),
	(13, 'What is cloning', 2, 13, 2),
	(14, 'Types of cloning', 2, 13, 1),
	(22, 'Class is an example of encapsulation', 1, 23, 2);
/*!40000 ALTER TABLE `tblquestion` ENABLE KEYS */;

-- Dumping structure for table ap.tblsecurityquestion
CREATE TABLE IF NOT EXISTS `tblsecurityquestion` (
  `securityQuestNumber` int(11) NOT NULL,
  `securityQuestions` varchar(100) NOT NULL,
  PRIMARY KEY (`securityQuestNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblsecurityquestion: ~2 rows (approximately)
/*!40000 ALTER TABLE `tblsecurityquestion` DISABLE KEYS */;
INSERT INTO `tblsecurityquestion` (`securityQuestNumber`, `securityQuestions`) VALUES
	(1, 'Which is your favorite sport in childhood?'),
	(2, 'Who was your favorite teacher in school days?'),
	(3, 'Which brand bike you like most?');
/*!40000 ALTER TABLE `tblsecurityquestion` ENABLE KEYS */;

-- Dumping structure for table ap.tbluseractivity
CREATE TABLE IF NOT EXISTS `tbluseractivity` (
  `user_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `result` varchar(10) DEFAULT NULL,
  `create_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tbluseractivity: ~29 rows (approximately)
/*!40000 ALTER TABLE `tbluseractivity` DISABLE KEYS */;
INSERT INTO `tbluseractivity` (`user_id`, `category_id`, `score`, `result`, `create_ts`) VALUES
	(1, 1, 80, 'Pass', '2018-03-20 17:30:41'),
	(1, 1, 30, 'Fail', '2018-03-20 17:31:35'),
	(1, 1, 20, 'Fail', '2018-03-20 17:31:39'),
	(1, 1, 80, 'Pass', '2018-03-20 17:31:42'),
	(1, 1, 10, 'Fail', '2018-03-20 17:31:45'),
	(1, 1, 90, 'Pass', '2018-03-20 17:31:48'),
	(1, 1, 10, 'Fail', '2018-03-20 17:31:50'),
	(1, 14, 10, 'Fail', '2018-03-20 17:43:02'),
	(1, 14, 80, 'Pass', '2018-03-20 17:43:17'),
	(1, 13, 66, 'Fail', '2018-03-20 17:54:11'),
	(1, 14, 100, 'Pass', '2018-03-20 17:57:14'),
	(1, 13, 0, 'Fail', '2018-03-20 18:07:49'),
	(3, 13, 33, 'Fail', '2018-03-20 18:19:37'),
	(3, 14, 0, 'Fail', '2018-03-20 18:20:36'),
	(1, 13, 0, 'Fail', '2018-03-20 18:35:49'),
	(1, 13, 33, 'Fail', '2018-03-20 19:04:44'),
	(1, 13, 40, 'Fail', '2018-03-20 19:20:51'),
	(6, 13, 0, 'Fail', '2018-03-21 10:56:53'),
	(7, 13, 0, 'Fail', '2018-03-21 16:17:00'),
	(7, 13, 33, 'Fail', '2018-03-21 16:17:18'),
	(7, 13, 0, 'Fail', '2018-03-21 16:17:35'),
	(7, 13, 0, 'Fail', '2018-03-21 16:17:51'),
	(7, 13, 0, 'Fail', '2018-03-21 16:29:59'),
	(8, 13, 0, 'Fail', '2018-03-21 16:31:40'),
	(8, 13, 0, 'Fail', '2018-03-21 16:31:51'),
	(8, 13, 0, 'Fail', '2018-03-21 16:32:19'),
	(8, 13, 0, 'Fail', '2018-03-21 16:32:55'),
	(8, 13, 0, 'Fail', '2018-03-21 16:34:41'),
	(7, 14, 0, 'Fail', '2018-03-21 19:33:34'),
	(6, 13, 33, 'Fail', '2018-03-22 13:45:53'),
	(7, 14, 33, 'Fail', '2018-03-23 12:35:27'),
	(19, 13, 25, 'Fail', '2018-03-23 12:53:20'),
	(19, 14, 66, 'Fail', '2018-03-23 13:06:32');
/*!40000 ALTER TABLE `tbluseractivity` ENABLE KEYS */;

-- Dumping structure for table ap.tblusers
CREATE TABLE IF NOT EXISTS `tblusers` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_role` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `securityQuestNumber` int(2) DEFAULT NULL,
  `securityAnswer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Dumping data for table ap.tblusers: ~17 rows (approximately)
/*!40000 ALTER TABLE `tblusers` DISABLE KEYS */;
INSERT INTO `tblusers` (`user_id`, `user_name`, `password`, `user_role`, `email`, `securityQuestNumber`, `securityAnswer`) VALUES
	(1, 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', 'test@test.com', 3, 'test'),
	(2, 'admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'admin', 'admin@test.com', 3, 'admin'),
	(3, 'DeepanKarthik', '96f0f08c0188ba04898ce8cc465c19c4', 'user', 'asd@asd.com', 1, 'cricket'),
	(4, 'vennila', 'cc03e747a6afbbcbf8be7668acfebee5', 'user', 'test@test.com', 1, 'cricket'),
	(5, 'balaji', '5f4dcc3b5aa765d61d8327deb882cf99', 'user', 'test@cognizant.com', 3, 'honda'),
	(6, 'guna', '6e6b00b1d17718deb07a0d6c231e2271', 'user', 'guna@gmail.com', 1, 'kabbadi'),
	(7, 'Test123', '68eacb97d86f0c4621fa2b0e17cabd8c', 'user', 'Test123@gmail.com', 1, 'Test123'),
	(8, 'Testing123', 'ac1c8d64fd23ae5a7eac5b7f7ffee1fa', 'user', 'Testing123@gmail.com', 1, 'Testing123'),
	(9, 'portal', 'b077fc4f752957c1966bc48d3ef9ad8a', 'user', 'portal@test.com', 2, 'mam'),
	(10, 'newuser', '5f4dcc3b5aa765d61d8327deb882cf99', 'user', 'test@portal.com', 3, 'bullet'),
	(11, 'reguser', '5f4dcc3b5aa765d61d8327deb882cf99', 'user', 'welcome@portal.com', 3, 'honda'),
	(12, 'siva', '1955b38f13116a57e4de2134a139d139', 'user', 'siva@gmail.com', 1, 'siva'),
	(13, 'test1', '5a105e8b9d40e1329780d62ea2265d8a', 'user', 'test1@gmail.com', 2, 'test1'),
	(14, 'dummy', '275876e34cf609db118f3d84b799a790', 'user', 'dummy@email.com', 3, 'dummy'),
	(15, 'test2', 'ad0234829205b9033196ba818f7a872b', 'user', 'test2@gmail.com', 2, 'test2'),
	(16, 'testuser', '5d9c68c6c50ed3d02a2fcf54f63993b6', 'user', 'test2@gmail.com', 2, 'test2'),
	(17, 'tester', 'f5d1278e8109edd94e1e4197e04873b9', 'user', 'tester@gmail.com', 3, 'tester'),
	(18, 'admin123', '192023a7bbd73250516f069df18b500', 'user', 'admin123@email.com', 2, 'admin123'),
	(19, 'manoj', '5e81f9859d223ea420aca993c647b839', 'user', 'manoj@gmail.com', 1, 'asdf');
/*!40000 ALTER TABLE `tblusers` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

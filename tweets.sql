-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql-28211-db.mysql-28211:28211
-- Generation Time: Jun 17, 2021 at 09:10 AM
-- Server version: 8.0.22
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tweets`
--

-- --------------------------------------------------------

--
-- Table structure for table `hash_tag`
--

CREATE TABLE `hash_tag` (
  `hash_tag_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hash_tag`
--

INSERT INTO `hash_tag` (`hash_tag_name`) VALUES
('#are'),
('#awesome'),
('#champions'),
('#coming'),
('#cool'),
('#hack'),
('#life'),
('#new'),
('#pera'),
('#something'),
('#tags'),
('#we'),
('#woho');

-- --------------------------------------------------------

--
-- Table structure for table `tweet`
--

CREATE TABLE `tweet` (
  `tweet_id` bigint NOT NULL,
  `tweet_body` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `date_created` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tweet`
--

INSERT INTO `tweet` (`tweet_id`, `tweet_body`, `username`, `date_created`) VALUES
(4, 'Hello World!', 'rastko', '2021-06-15 22:15:01'),
(5, 'Hello World again!', 'rastko', '2021-06-15 22:15:08'),
(16, 'Some new tweet post!', 'rastko', '2021-06-16 11:58:15'),
(17, 'Some new tweet post!', 'rastko', '2021-06-16 12:06:40'),
(18, 'Some new tweet post from pera!', 'pera', '2021-06-16 12:38:30'),
(21, 'Some new tweet post from rastko mitrovic!', 'rastko', '2021-06-16 13:44:36');

-- --------------------------------------------------------

--
-- Table structure for table `tweets_user`
--

CREATE TABLE `tweets_user` (
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tweets_user`
--

INSERT INTO `tweets_user` (`username`) VALUES
('pera'),
('rastko');

-- --------------------------------------------------------

--
-- Table structure for table `tweet_hash_tag`
--

CREATE TABLE `tweet_hash_tag` (
  `tweet_id` bigint NOT NULL,
  `hash_tag_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tweet_hash_tag`
--

INSERT INTO `tweet_hash_tag` (`tweet_id`, `hash_tag_name`) VALUES
(21, '#are'),
(17, '#awesome'),
(21, '#coming'),
(4, '#life'),
(5, '#life'),
(16, '#new'),
(17, '#new'),
(18, '#new'),
(18, '#pera'),
(21, '#tags'),
(16, '#woho');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hash_tag`
--
ALTER TABLE `hash_tag`
  ADD PRIMARY KEY (`hash_tag_name`);

--
-- Indexes for table `tweet`
--
ALTER TABLE `tweet`
  ADD PRIMARY KEY (`tweet_id`),
  ADD KEY `tweet___fk_user` (`username`);

--
-- Indexes for table `tweets_user`
--
ALTER TABLE `tweets_user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tweet_hash_tag`
--
ALTER TABLE `tweet_hash_tag`
  ADD PRIMARY KEY (`tweet_id`,`hash_tag_name`),
  ADD KEY `tweet_hash_tag___fk_hash_tag` (`hash_tag_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tweet`
--
ALTER TABLE `tweet`
  MODIFY `tweet_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tweet`
--
ALTER TABLE `tweet`
  ADD CONSTRAINT `tweet___fk_user` FOREIGN KEY (`username`) REFERENCES `tweets_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tweet_hash_tag`
--
ALTER TABLE `tweet_hash_tag`
  ADD CONSTRAINT `tweet_hash_tag___fk_hash_tag` FOREIGN KEY (`hash_tag_name`) REFERENCES `hash_tag` (`hash_tag_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tweet_hash_tag___fk_tweet` FOREIGN KEY (`tweet_id`) REFERENCES `tweet` (`tweet_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

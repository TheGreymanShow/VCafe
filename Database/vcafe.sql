-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2017 at 08:15 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vcafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `Sr no` int(11) NOT NULL,
  `RollNo` text NOT NULL,
  `TotalBalance` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`Sr no`, `RollNo`, `TotalBalance`) VALUES
(1, '14102A0001', 100),
(2, '14102A0002', 100),
(3, '14102A0003', 100),
(4, '14102A0004', 100),
(5, '14102A0005', 100);

-- --------------------------------------------------------

--
-- Table structure for table `canteen`
--

CREATE TABLE `canteen` (
  `id` int(11) NOT NULL,
  `ItemName` varchar(75) NOT NULL,
  `Image` varchar(75) DEFAULT NULL,
  `Cost` float NOT NULL,
  `Availability` int(11) NOT NULL,
  `Description` varchar(300) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `canteen`
--

INSERT INTO `canteen` (`id`, `ItemName`, `Image`, `Cost`, `Availability`, `Description`) VALUES
(1, 'WristWatch', 'images/b.jpg', 1299, 10, 'Men''s classic wrist watch which is waterproof and warranty of 4 years.'),
(2, 'SportsShoes', 'images/d.jpg', 999, 5, 'Super comfortable and sporty looking shoes with a 5 year warranty.'),
(3, 'Headphones', 'images/e.jpg', 799, 10, 'Over the ear headphones with excellent bass.');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `RollNo` varchar(75) NOT NULL,
  `Name` varchar(75) NOT NULL,
  `Type` varchar(75) DEFAULT NULL,
  `Class` varchar(75) NOT NULL,
  `Year` varchar(75) NOT NULL,
  `Branch` varchar(75) NOT NULL,
  `Balance` float NOT NULL,
  `Password` varchar(75) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`RollNo`, `Name`, `Type`, `Class`, `Year`, `Branch`, `Balance`, `Password`) VALUES
('14102A0001', 'Sujay Jagtap', 'Student', 'A', 'TE', 'CMPN', 100, 'aaaa'),
('14102A0002', 'Kunal Raut', 'Student', 'A', 'TE', 'CMPN', 100, 'bbbb'),
('14102A0003', 'Akshay Damankar', 'Student', 'A', 'TE', 'CMPN', 100, 'cccc'),
('14102A0004', 'Meghna Pai', 'Student', 'A', 'TE', 'CMPN', 100, 'dddd'),
('14102A0005', 'Raja Parmar', 'Student', 'A', 'TE', 'CMPN', 100, 'eeee'),
('14102A0069', 'Sanit Rajula', 'student', 'A', 'TE', 'cmpn', 10000, 'sanit');

-- --------------------------------------------------------

--
-- Table structure for table `temp_orders`
--

CREATE TABLE `temp_orders` (
  `SrNo` int(11) NOT NULL,
  `RollNo` varchar(75) NOT NULL,
  `ItemName` varchar(75) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `temp_orders`
--

INSERT INTO `temp_orders` (`SrNo`, `RollNo`, `ItemName`, `Quantity`, `Price`) VALUES
(1, '14102A0002', 'WristWatch', 1, 1299),
(2, '14102A0002', 'SportsShoes', 1, 999),
(3, '14102A0002', 'WristWatch', 1, 1299),
(4, '14102A0002', 'SportsShoes', 1, 999),
(5, '14102A0002', 'Headphones', 1, 799),
(6, '14102A0002', 'WristWatch', 1, 1299),
(7, '14102A0002', 'Headphones', 1, 799),
(8, '14102A0001', 'WristWatch', 3, 3897),
(9, '14102A0001', 'SportsShoes', 2, 1998),
(10, '14102A0001', 'WristWatch', 1, 1299),
(11, '14102A0001', 'SportsShoes', 3, 2997),
(12, '14102A0001', 'Headphones', 4, 3196),
(13, '14102A0001', 'WristWatch', 1, 1299),
(14, '14102A0001', 'SportsShoes', 3, 2997),
(15, '14102A0001', 'WristWatch', 1, 1299),
(16, '14102A0001', 'Headphones', 2, 1598),
(17, '14102A0001', 'WristWatch', 1, 1299),
(18, '14102A0001', 'Headphones', 2, 1598),
(19, '14102A0001', 'SportsShoes', 5, 4995),
(20, '14102A0069', 'WristWatch', 1, 1299),
(21, '14102A0069', 'Headphones', 2, 1598),
(22, '14102A0069', 'SportsShoes', 5, 4995),
(23, '14102A0069', 'WristWatch', 1, 1299),
(24, '14102A0069', 'Headphones', 2, 1598),
(25, '14102A0069', 'SportsShoes', 5, 4995);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`Sr no`);

--
-- Indexes for table `canteen`
--
ALTER TABLE `canteen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`RollNo`),
  ADD UNIQUE KEY `RollNo` (`RollNo`),
  ADD UNIQUE KEY `Password` (`Password`),
  ADD UNIQUE KEY `Password_2` (`Password`);

--
-- Indexes for table `temp_orders`
--
ALTER TABLE `temp_orders`
  ADD PRIMARY KEY (`SrNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `Sr no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `canteen`
--
ALTER TABLE `canteen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `temp_orders`
--
ALTER TABLE `temp_orders`
  MODIFY `SrNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

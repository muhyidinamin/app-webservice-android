-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2017 at 03:49 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_upwork`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_client`
--

CREATE TABLE `tb_client` (
  `Depan` varchar(50) NOT NULL,
  `Belakang` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Perusahaan` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_job`
--

CREATE TABLE `tb_job` (
  `Id` int(11) NOT NULL,
  `Judul` varchar(50) NOT NULL,
  `Budget` int(11) NOT NULL,
  `Desk` text NOT NULL,
  `Username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_job`
--

INSERT INTO `tb_job` (`Id`, `Judul`, `Budget`, `Desk`, `Username`) VALUES
(1, 'Data Entry', 100000, 'dhjfkasjhdaasjhkdjahsd', '2017-05-22'),
(2, 'Mengecat Lantai', 900000, 'zkldsjaklsdjalskdja', '2017-05-17');

-- --------------------------------------------------------

--
-- Table structure for table `tb_profile`
--

CREATE TABLE `tb_profile` (
  `Username` varchar(50) NOT NULL,
  `Keahlian` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL,
  `Deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_proposal`
--

CREATE TABLE `tb_proposal` (
  `Id` int(11) NOT NULL,
  `Tawaran` int(11) NOT NULL,
  `Proposal` text NOT NULL,
  `Username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_worker`
--

CREATE TABLE `tb_worker` (
  `Depan` varchar(50) NOT NULL,
  `Belakang` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_worker`
--

INSERT INTO `tb_worker` (`Depan`, `Belakang`, `Email`, `Username`, `Password`) VALUES
('Muhyi', 'Muhyidin Amin', 'muh', 'muh@gmail.com', 'muhyi'),
('Muhamad', 'Muhyidin Amin', 'muhyidin@gmail.com', 'muhyi', '12345'),
('Muhamad Amin', 'Amin', 'amin@gmail.com', 'muhyidin', 'amin'),
('yu', 'yu', 'yu', 'yu', 'yu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_client`
--
ALTER TABLE `tb_client`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `tb_job`
--
ALTER TABLE `tb_job`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `tb_proposal`
--
ALTER TABLE `tb_proposal`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `tb_worker`
--
ALTER TABLE `tb_worker`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_job`
--
ALTER TABLE `tb_job`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_proposal`
--
ALTER TABLE `tb_proposal`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

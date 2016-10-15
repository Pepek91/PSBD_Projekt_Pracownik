-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 28, 2014 at 10:05 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `psbd`
--
CREATE DATABASE IF NOT EXISTS `psbd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `psbd`;

-- --------------------------------------------------------

--
-- Table structure for table `hotele`
--

CREATE TABLE IF NOT EXISTS `hotele` (
  `id_hotelu` int(11) NOT NULL AUTO_INCREMENT,
  `adres_kod` varchar(6) DEFAULT NULL,
  `adres_miasto` varchar(255) DEFAULT NULL,
  `adres_ulica` varchar(255) DEFAULT NULL,
  `nazwa` varchar(45) DEFAULT NULL,
  `liczba_gwiazdek` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_hotelu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `hotele`
--

INSERT INTO `hotele` (`id_hotelu`, `adres_kod`, `adres_miasto`, `adres_ulica`, `nazwa`, `liczba_gwiazdek`) VALUES
(1, NULL, 'Kielce', NULL, 'Róża', 3),
(2, NULL, 'Warszawa', NULL, 'Pod jeleniem', 5),
(3, NULL, 'Kraków', NULL, 'Leśne zacisze', 3);

-- --------------------------------------------------------

--
-- Table structure for table `karty`
--

CREATE TABLE IF NOT EXISTS `karty` (
  `id_karty` int(11) NOT NULL AUTO_INCREMENT,
  `id_rezerwacji` int(11) DEFAULT NULL,
  `numer_karty` int(11) DEFAULT NULL,
  `rodzaj` varchar(45) DEFAULT NULL,
  `kod_bezpieczenstwa` int(11) DEFAULT NULL,
  `data_waznosci` date DEFAULT NULL,
  PRIMARY KEY (`id_karty`),
  KEY `id_rezerwacji_idx` (`id_rezerwacji`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `klienci`
--

CREATE TABLE IF NOT EXISTS `klienci` (
  `id_klienta` int(11) NOT NULL AUTO_INCREMENT,
  `e_mail` varchar(255) DEFAULT NULL,
  `haslo` varchar(255) DEFAULT NULL,
  `numer_dowodu` varchar(45) DEFAULT NULL,
  `imie` varchar(255) DEFAULT NULL,
  `nazwisko` varchar(255) DEFAULT NULL,
  `liczba_pobytow` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_klienta`),
  UNIQUE KEY `e_mail_UNIQUE` (`e_mail`),
  UNIQUE KEY `Numer_dowodu_UNIQUE` (`numer_dowodu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `klienci`
--

INSERT INTO `klienci` (`id_klienta`, `e_mail`, `haslo`, `numer_dowodu`, `imie`, `nazwisko`, `liczba_pobytow`) VALUES
(1, 'm', 'c4ca4238a0b923820dcc509a6f75849b', '1', 'Michał', 'Stępień', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pokoje`
--

CREATE TABLE IF NOT EXISTS `pokoje` (
  `id_pokoju` int(11) NOT NULL AUTO_INCREMENT,
  `id_hotelu` int(11) NOT NULL,
  `numer_pokoju` int(11) unsigned NOT NULL,
  `cena` float unsigned NOT NULL,
  `standard` tinyint(3) unsigned NOT NULL,
  `liczba_osob` tinyint(4) unsigned NOT NULL,
  `liczba_pomieszczen` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`id_pokoju`),
  KEY `id_holtelu_idx` (`id_hotelu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `pokoje`
--

INSERT INTO `pokoje` (`id_pokoju`, `id_hotelu`, `numer_pokoju`, `cena`, `standard`, `liczba_osob`, `liczba_pomieszczen`) VALUES
(1, 1, 1, 50, 2, 3, 0),
(2, 1, 1, 50, 2, 3, 0),
(3, 1, 2, 70, 3, 2, 1),
(4, 2, 1, 100, 3, 4, 2),
(5, 2, 2, 100, 3, 4, 2),
(6, 2, 3, 120, 2, 1, 1),
(7, 2, 4, 140, 2, 2, 1),
(8, 2, 5, 1220, 4, 3, 2),
(9, 3, 1, 200, 3, 3, 2),
(10, 3, 2, 4400, 4, 6, 3),
(11, 3, 3, 190, 2, 2, 1),
(12, 3, 4, 40, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pokoje_rezerwacje`
--

CREATE TABLE IF NOT EXISTS `pokoje_rezerwacje` (
  `id_rezerwacji` int(11) NOT NULL,
  `id_pokoju` int(11) NOT NULL,
  `cena` float DEFAULT NULL,
  `liczba_gosci` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_rezerwacji`,`id_pokoju`),
  KEY `id_pokoju_idx` (`id_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pokoje_rezerwacje`
--

INSERT INTO `pokoje_rezerwacje` (`id_rezerwacji`, `id_pokoju`, `cena`, `liczba_gosci`) VALUES
(6, 1, 50, 3),
(6, 2, 50, 3),
(6, 3, 70, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pracownicy`
--

CREATE TABLE IF NOT EXISTS `pracownicy` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `e_mail` varchar(255) NOT NULL,
  `haslo` varchar(45) NOT NULL,
  `imie` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `uprawnienia` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_pracownika`),
  UNIQUE KEY `e_mail_UNIQUE` (`e_mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `pracownicy`
--

INSERT INTO `pracownicy` (`id_pracownika`, `e_mail`, `haslo`, `imie`, `nazwisko`, `uprawnienia`) VALUES
(1, 'm', 'c4ca4238a0b923820dcc509a6f75849b', 'Pani', 'Gienia', 1),
(3, 'a', 'c4ca4238a0b923820dcc509a6f75849b', 'Pan', 'Stanisław', 2);

-- --------------------------------------------------------

--
-- Table structure for table `pracownicy_rezerwacje`
--

CREATE TABLE IF NOT EXISTS `pracownicy_rezerwacje` (
  `id_pracownika` int(11) NOT NULL,
  `id_rezerwacji` int(11) NOT NULL,
  `data_ostatniej_modyfikacji` date DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pracownika`,`id_rezerwacji`),
  KEY `id_rezerwacji_idx` (`id_rezerwacji`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rezerwacje`
--

CREATE TABLE IF NOT EXISTS `rezerwacje` (
  `id_rezerwacji` int(11) NOT NULL AUTO_INCREMENT,
  `id_klienta` int(11) DEFAULT NULL,
  `data_rozpoczecia` date DEFAULT NULL,
  `data_zakonczenia` date DEFAULT NULL,
  `status` enum('aktywna','nieaktywna','kara','zameldowany','wymeldowany') DEFAULT NULL,
  `znizka` smallint(6) DEFAULT NULL,
  `kara` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_rezerwacji`),
  KEY `id_klient_idx` (`id_klienta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `rezerwacje`
--

INSERT INTO `rezerwacje` (`id_rezerwacji`, `id_klienta`, `data_rozpoczecia`, `data_zakonczenia`, `status`, `znizka`, `kara`) VALUES
(6, 1, '2014-01-01', '2014-01-10', 'aktywna', 0, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karty`
--
ALTER TABLE `karty`
  ADD CONSTRAINT `id_rezerwacji` FOREIGN KEY (`id_rezerwacji`) REFERENCES `rezerwacje` (`id_rezerwacji`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pokoje`
--
ALTER TABLE `pokoje`
  ADD CONSTRAINT `id_holtelu` FOREIGN KEY (`id_hotelu`) REFERENCES `hotele` (`id_hotelu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pokoje_rezerwacje`
--
ALTER TABLE `pokoje_rezerwacje`
  ADD CONSTRAINT `id_pokoju` FOREIGN KEY (`id_pokoju`) REFERENCES `pokoje` (`id_pokoju`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_rezerwacji_pokoje` FOREIGN KEY (`id_rezerwacji`) REFERENCES `rezerwacje` (`id_rezerwacji`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pracownicy_rezerwacje`
--
ALTER TABLE `pracownicy_rezerwacje`
  ADD CONSTRAINT `id_pracownika` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownicy` (`id_pracownika`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_rezerwacji_pracownicy` FOREIGN KEY (`id_rezerwacji`) REFERENCES `rezerwacje` (`id_rezerwacji`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rezerwacje`
--
ALTER TABLE `rezerwacje`
  ADD CONSTRAINT `id_klient` FOREIGN KEY (`id_klienta`) REFERENCES `klienci` (`id_klienta`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

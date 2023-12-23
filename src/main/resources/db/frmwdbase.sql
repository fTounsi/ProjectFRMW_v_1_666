-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: database
-- Generation Time: Dec 23, 2023 at 02:35 AM
-- Server version: 10.6.4-MariaDB-1:10.6.4+maria~focal
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `frmwdbase`
--

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `club_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `logo_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  `cin_entraineur` varchar(255) DEFAULT NULL,
  `cin_president` varchar(255) DEFAULT NULL,
  `code` bigint(20) DEFAULT NULL,
  `date_assembleeg` datetime(6) DEFAULT NULL,
  `entraineur` varchar(255) DEFAULT NULL,
  `president` varchar(255) DEFAULT NULL,
  `tel_entraineur` varchar(255) DEFAULT NULL,
  `tel_president` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`club_id`, `address`, `created_date`, `logo_path`, `name`, `telephone`, `region_id`, `cin_entraineur`, `cin_president`, `code`, `date_assembleeg`, `entraineur`, `president`, `tel_entraineur`, `tel_president`) VALUES
(1, 'Hay El Wifaq, Temara', '2023-11-09 00:00:00.000000', NULL, 'CLUB WUSHU TEMARA', '+2126629077689', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Hay Riad, Rabat', '2023-11-21 00:00:00.000000', NULL, 'CLUB SANDA Tigers', '+2127366477689', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'N16 Gueliz, MArrakech ', '2023-11-21 00:00:00.000000', NULL, 'CLUB ISMAILI FONOUN AL QITAL', '+2127366477689', 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Ocean, Rabat ', '2023-11-23 00:00:00.000000', NULL, 'CLUB ALYAQIN', '+212666666666', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'California 06, Casablanca', '2023-11-21 00:00:00.000000', NULL, 'CLUB Kong Fu Wushu NIPPON', '+2126905867689', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '111, Bourgogne rue Bordeaux, Casablanca', '2023-11-21 00:00:00.000000', NULL, 'CLUB Bourgogne', '+2126666677689', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'G5, 129 Bv koora, CYM', '2023-11-23 00:00:00.000000', NULL, 'CLUB FIGHT4EVER', '213232323', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'Maarif, bv Zreqtoni N 623', '2023-11-21 00:00:00.000000', NULL, 'NOUVEAU_CLUBBBBB', '0671070673', 28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'Maarif, bv Zreqtoni N 623', '2023-11-23 00:00:00.000000', NULL, 'CLUB FONOUN ALKITAL', '0671070673', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'Maarif, bv Zreqtoni N 623', '2023-11-23 00:00:00.000000', NULL, 'Club Martial Arts Maarif', '0671070673', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `competition`
--

CREATE TABLE `competition` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ending_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type_comp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `competition`
--

INSERT INTO `competition` (`id`, `description`, `ending_date`, `name`, `place`, `start_date`, `status`, `type_comp`) VALUES
(1, 'The 15th World Wushu Championships بطولة العالم للووشو المقامة في شنغهاي ما بين 16 و 26 اكتوبر 2019 ؛ البطولة التي يشارك فيها أكثر من ثمانون منتخبا من كل أنحاء العالم في مختلف أسابيب الووشو إضافة الى مشاركة منتخب المغرب', '2023-12-01 00:00:00.000000', 'The 15th World Wushu Championships', 'Temara', '2023-11-26 00:00:00.000000', 'EN COURS', 'Sanda'),
(2, 'Championnat National Sanda des Enfants et Adultes', '2023-12-03 00:00:00.000000', 'Championnat National Sanda des Enfants et Adultes', 'Casablanca', '2023-11-29 00:00:00.000000', 'EN COURS', 'Sanda'),
(3, 'Passage de Grade Ceinture Noire', '2023-12-01 00:00:00.000000', 'Passage de Grade Ceinture Noire', 'Rabat', '2023-11-26 00:00:00.000000', 'EN COURS', 'Sanda'),
(4, 'Championnat Africaine de Taolu Wushu ', '2023-12-01 00:00:00.000000', 'Championnat Africaine de Taolu Wushu ', 'Complexe Mly Abdellah, Rabat', '2023-11-26 00:00:00.000000', 'EN COURS', 'Sanda');

-- --------------------------------------------------------

--
-- Table structure for table `competition_matches`
--

CREATE TABLE `competition_matches` (
  `competition_id` bigint(20) NOT NULL,
  `matches_match_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `competition_participated_clubs`
--

CREATE TABLE `competition_participated_clubs` (
  `competitions_id` bigint(20) NOT NULL,
  `participated_clubs_club_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `competition_participated_clubs`
--

INSERT INTO `competition_participated_clubs` (`competitions_id`, `participated_clubs_club_id`) VALUES
(4, 1),
(4, 3),
(4, 4),
(4, 6),
(4, 8),
(4, 11),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 8),
(4, 9),
(4, 10),
(4, 11);

-- --------------------------------------------------------

--
-- Table structure for table `competition_participated_members`
--

CREATE TABLE `competition_participated_members` (
  `competitions_id` bigint(20) NOT NULL,
  `participated_members_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `competition_participated_members`
--

INSERT INTO `competition_participated_members` (`competitions_id`, `participated_members_id`) VALUES
(4, 14),
(4, 24),
(4, 25),
(4, 12),
(4, 11),
(4, 13),
(4, 9),
(4, 18),
(4, 20),
(4, 22),
(4, 19);

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `match_id` bigint(20) NOT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL CHECK (`status` between 0 and 2),
  `blue_player_id` bigint(20) DEFAULT NULL,
  `competition_id` bigint(20) DEFAULT NULL,
  `red_player_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `club_id` bigint(20) DEFAULT NULL,
  `club_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `address`, `birth_date`, `cin`, `email`, `first_name`, `gender`, `height`, `last_name`, `photo_path`, `telephone`, `weight`, `club_id`, `club_name`) VALUES
(1, 'Addressa 12, Rabat', '2023-11-14 00:00:00.000000', 'AA2773', 'aa@bb.cc', 'Amine', 'H', 0, 'Moussaoui', 'member2972.jpeg', '+2127366477689', 0, 1, NULL),
(2, 'Addressa 12, Casa', '2023-11-14 00:00:00.000000', 'AA2778', 'aa@bb.cc', 'Rami', 'H', 0, 'Rez', '', '+212736882639', 0, 6, NULL),
(3, 'Addressa 12, Tokio', '2023-11-14 00:00:00.000000', 'E66273', 'aa@bb.cc', 'Rock', 'H', 0, 'Lee', 'member2972.jpeg', '+21266666689', 0, 6, NULL),
(4, 'Addressa 12, Rabat', '2023-11-14 00:00:00.000000', 'GH88372', 'aa@bb.cc', 'Younes', 'H', 0, 'Samlali', 'satoshi.png', '+21263222689', 0, 1, NULL),
(5, 'Addressa 12, Casa', '2023-11-14 00:00:00.000000', 'D377438', 'aa@bb.cc', 'Aziza', 'F', 0, 'Mahiri', '', '+212655533689', 0, 5, NULL),
(6, 'Addressa 12, Casa', '2023-11-14 00:00:00.000000', 'AB33252', 'aa@bb.cc', 'Wydad', 'F', 0, 'Tahiri', '', '+212622245389', 0, 5, NULL),
(7, 'Addressa 12, Casa', '2023-11-14 00:00:00.000000', 'E55369', 'aa@bb.cc', 'Rajae', 'F', 0, 'Mirtab', '', '+2127366477689', 0, 4, NULL),
(8, 'Addressa 12, Marrakech', '2023-11-14 00:00:00.000000', 'X883674', 'aa@bb.cc', 'Hicham', 'H', 0, 'Saleh', 'satoshi.png', '+2126898989689', 0, 3, NULL),
(9, 'Addressa 12, NY', '2023-11-14 00:00:00.000000', 'F66639', 'aa@bb.cc', 'Bruce', 'H', 0, 'Lee', 'naziiiizz.png', '+2126332137689', 0, 1, NULL),
(10, 'Addressa 12, Fes', '2023-11-14 00:00:00.000000', 'C5443677', 'aa@bb.cc', 'Karim', 'H', 0, 'Ghali', '', '+2127366477689', 0, 5, NULL),
(11, 'Addressa 12, Rabat', '2023-11-14 00:00:00.000000', 'H7736', 'aa@bb.cc', 'Imad', 'H', 0, 'Kadiri', '', '+2127366477689', 0, 2, NULL),
(12, 'Addressa 12, Rabat', '2023-11-14 00:00:00.000000', 'E44133', 'aa@bb.cc', 'Flan', 'H', 0, 'Fertelan', 'satoshi.png', '+2126422444489', 0, 2, NULL),
(13, 'Addressa 12, Rabat', '2023-11-14 00:00:00.000000', 'H77365', 'aa@bb.cc', 'Maha', 'F', 0, 'Naoum', 'naziiiizz.png', '+2127366477689', 0, 2, NULL),
(14, 'Maarif, bv Zreqtoni N 623', '2023-11-14 00:00:00.000000', 'AB55363', 'fouad.tounsi@gmail.com', 'Mencef', 'M', 177, 'Khlifi', 'member2.jpeg', '0671070673', 43, 1, NULL),
(15, 'Hay El Menzah N 1292 CYM', '1996-12-12 00:00:00.000000', 'AA9938', 'fouad.tounsi@gmail.com', 'AMRAOUI', 'M', 177, 'Hicham', 'member2.jpeg', '0671070673', 88, 5, NULL),
(16, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'K99590', 'fouad.tounsi@gmail.com', 'KKKKK', 'F', 153, 'KKKKK', 'image1696637810052.png', '0671070673', 54, 6, NULL),
(17, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'D9948', 'fouad.tounsi@gmail.com', 'NABIL', 'M', 0, 'IMRAN', '', '0671070673', 0, 1, NULL),
(18, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'R552367', 'fouad.tounsi@gmail.com', 'KJNK', 'M', 178, 'Oppp', 'satoshi.png', '0671070673', 77, 1, NULL),
(19, 'Maarif, bv Zreqtoni N 623', '2023-11-14 00:00:00.000000', 'J88461', 'fouad.tounsi@gmail.com', 'Fouad', 'M', 166, 'Tounsi', 'member1.jpeg', '0671070673', 66, 1, NULL),
(20, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'Z44353', 'fouad.tounsi@gmail.com', 'Rock', 'F', 155, 'Lee', 'CRYPTOPUNK_a1.png', '0671070673', 56, 1, NULL),
(21, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'F6637', 'fouad.tounsi@gmail.com', 'XXXXX', 'M', 178, 'XXXXX', 'naziiiizz.png', '0671070673', 88, 4, NULL),
(22, 'Maarif, bv Zreqtoni N 623', '1996-12-12 00:00:00.000000', 'KL9983', 'fouad.tounsi@gmail.com', 'Lee', 'F', 167, 'Yun', 'member1.jpeg', '0671070673', 56, 1, NULL),
(23, 'Hay El Menzah N 1292 CYM', '2020-08-01 00:00:00.000000', 'AD443553', 'rrrrrrrr@gmail.com', 'Hicham', 'F', 166, 'RRRRRRRRRR', 'naziiiizz.png', '0671070673', 66, 11, NULL),
(24, 'Maarif, bv Zreqtoni N 623', NULL, 'AB66647', 'fouad.tounsi@gmail.com', 'Fouad', 'M', 0, 'Tounsi', '', '0671070673', 0, 1, NULL),
(25, 'Maarif, bv Zreqtoni N 623', '2023-02-12 00:00:00.000000', 'Q24324', 'fouad.tounsi@gmail.com', 'Khalid', 'M', 187, 'Khalid', '', '0671070673', 88, 1, NULL),
(26, 'Maarif, bv Zreqtoni N 623', NULL, 'A1234', 'fouad.tounsi@gmail.com', 'Will', 'M', 1878, 'TREX', NULL, '0671070673', 1878, 1, NULL),
(27, 'Hay El Menzah N 1292 CYM', NULL, 'A12345', 'fouad.tounsi@gmail.com', 'UYHBB', 'M', 167, 'MOUIOUE', 'member1.jpeg', '0671070673', 77, 10, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `member_registrations`
--

CREATE TABLE `member_registrations` (
  `member_id` bigint(20) NOT NULL,
  `registrations_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `region`
--

CREATE TABLE `region` (
  `region_id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `date_assembleeg` datetime(6) DEFAULT NULL,
  `president` varchar(255) DEFAULT NULL,
  `secretaireg` varchar(255) DEFAULT NULL,
  `siege_adresse` varchar(255) DEFAULT NULL,
  `tel_president` varchar(255) DEFAULT NULL,
  `tel_secretaireg` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `cin_president` varchar(255) DEFAULT NULL,
  `cin_secretaireg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `region`
--

INSERT INTO `region` (`region_id`, `code`, `label`, `date_assembleeg`, `president`, `secretaireg`, `siege_adresse`, `tel_president`, `tel_secretaireg`, `telephone`, `cin_president`, `cin_secretaireg`) VALUES
(1, '01', 'Chaouia-Ouardighaaaaaaa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '02', 'Doukkala-Abda', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '03', 'Fès-Boulemane', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '04', 'Gharb-Chrarda-Beni Hssen', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '05', 'Grand Casablanca', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '06', 'Guelmim-Es Semara', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, '07', 'Laâyoune-Boujdour-Sakia el Hamr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, '08', 'Marrakech-Tensift-Al Haouz', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, '09', 'Meknès-Tafilalet', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, '10', 'L\'Oriental', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, '11', 'Oued ed Dahab-Lagouira', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, '12', 'Rabat-Salé-Zemmour-Zaër', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, '13', 'Tadla-Azilal', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, '14', 'Tanger-Tétouan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, '15', 'Souss-Massa-Drâa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, '16', 'Taza-Al Hoceïma-Taounate', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(28, '16', 'NEWREGION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(29, '27', 'KKAJJDSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, 'NRI', 'OLDREGION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '27', 'KKAJJDSNTTTTTTTT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(35, '1212', 'CHAMAL', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, '877310', 'SSSSSSS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, 'll', 'NEWREGIONklkl', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` bigint(20) NOT NULL,
  `registration_date` date DEFAULT NULL,
  `competition_id` bigint(20) DEFAULT NULL,
  `participant_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `registration_competitions`
--

CREATE TABLE `registration_competitions` (
  `registrations_id` bigint(20) NOT NULL,
  `competitions_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'STAFF');

-- --------------------------------------------------------

--
-- Table structure for table `round`
--

CREATE TABLE `round` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `roud_order` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL CHECK (`status` between 0 and 2),
  `match_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `id` bigint(20) NOT NULL,
  `arbitre_name` varchar(255) DEFAULT NULL,
  `blue_score` int(11) NOT NULL,
  `red_score` int(11) NOT NULL,
  `round_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 1),
(2, 1),
(2, 1),
(17, 3),
(18, 3);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `club_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `enabled`, `first_name`, `last_name`, `login`, `password`, `photo_path`, `club_id`) VALUES
(1, b'0', 'Fouad Tounsi', '', 'fouad', '$2a$10$GNuGG/.Zz8xYWK0OzHIlL.Vv1tLwV1qUVS4aGrgMTubuyX4HtwT.i', 'member1.jpeg', NULL),
(2, b'0', 'Esannaghi Abderrahmane', 'User', 'admin', '$2a$10$GNuGG/.Zz8xYWK0OzHIlL.Vv1tLwV1qUVS4aGrgMTubuyX4HtwT.i', ' Esannaghi-Avatar.jpeg', NULL),
(17, b'0', 'Ali Anizar', NULL, 'anizar', '$2a$10$k2lvLqLKBABddVAogp4Tze8BcHyUBAxAgKZDziTrgmBKAsblQp6nu', 'avatar5_be12d662-aa8a-4233-ae55-be2c26565b6d_b2e9cd96-1542-4bd6-bff4-ad91c8ac23c5.png', 2),
(18, b'0', 'Achraf Achraf', NULL, 'achraf', '$2a$10$GNuGG/.Zz8xYWK0OzHIlL.Vv1tLwV1qUVS4aGrgMTubuyX4HtwT.i', 'avatar4_8afb7734-084e-4db7-afc6-360294e2e9da.png', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`club_id`),
  ADD KEY `FKamx0dxm0l2fexpj6enmdclaw7` (`region_id`);

--
-- Indexes for table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `competition_matches`
--
ALTER TABLE `competition_matches`
  ADD KEY `FKqgn3fc5m5hmwqurtt08eo68up` (`competition_id`),
  ADD KEY `FK8pnsf6a6h9bm5r7kyg5o6xusa` (`matches_match_id`);

--
-- Indexes for table `competition_participated_clubs`
--
ALTER TABLE `competition_participated_clubs`
  ADD KEY `FKn1wvjkdiam7d8vudtthao9s35` (`participated_clubs_club_id`),
  ADD KEY `FKrs9s542i0a7nen8f9vfheehv9` (`competitions_id`);

--
-- Indexes for table `competition_participated_members`
--
ALTER TABLE `competition_participated_members`
  ADD KEY `FKt9g3gnyxv4kjkvpm2g578xavx` (`participated_members_id`),
  ADD KEY `FKriw5jwrl0i9bj57vdkp8us4o7` (`competitions_id`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `FKfe3yl5sr1yofrbqdpeethdtm6` (`blue_player_id`),
  ADD KEY `FKqdsihqr13n8mhhci86rvjnken` (`competition_id`),
  ADD KEY `FK5g9ub0a63rlx0ubh77i3gt2jr` (`red_player_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlb5erj72ba5omm5wpkrfwvngr` (`club_id`);

--
-- Indexes for table `member_registrations`
--
ALTER TABLE `member_registrations`
  ADD UNIQUE KEY `UK_lo6uhvx8v4al7i1jy8g6flcbp` (`registrations_id`),
  ADD KEY `FKj4o3eplhvmfr97wratm8jrxxq` (`member_id`);

--
-- Indexes for table `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`region_id`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9fk3n3ncmwvguy8ex9y46ymud` (`competition_id`),
  ADD KEY `FKgjv7ylejhs7by5b81rq3dlruo` (`participant_id`);

--
-- Indexes for table `registration_competitions`
--
ALTER TABLE `registration_competitions`
  ADD KEY `FKkxpngxeiiidronei15f8bg14n` (`competitions_id`),
  ADD KEY `FKd0vfph3i287i74k240i3tor5k` (`registrations_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `round`
--
ALTER TABLE `round`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3iae81a1e63i7clqi5fdkmwf3` (`match_id`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpp6i5qd5su7jye4dgrw2ot213` (`round_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  ADD KEY `FKfhg6g8mbl6gcwupy69tajpvjm` (`user_id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbaw4yx3a33io4q601i88bqpkv` (`club_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `club`
--
ALTER TABLE `club`
  MODIFY `club_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `competition`
--
ALTER TABLE `competition`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `match_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `region`
--
ALTER TABLE `region`
  MODIFY `region_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `round`
--
ALTER TABLE `round`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `FKamx0dxm0l2fexpj6enmdclaw7` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`);

--
-- Constraints for table `competition_matches`
--
ALTER TABLE `competition_matches`
  ADD CONSTRAINT `FK8pnsf6a6h9bm5r7kyg5o6xusa` FOREIGN KEY (`matches_match_id`) REFERENCES `matches` (`match_id`),
  ADD CONSTRAINT `FKqgn3fc5m5hmwqurtt08eo68up` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`);

--
-- Constraints for table `competition_participated_clubs`
--
ALTER TABLE `competition_participated_clubs`
  ADD CONSTRAINT `FKn1wvjkdiam7d8vudtthao9s35` FOREIGN KEY (`participated_clubs_club_id`) REFERENCES `club` (`club_id`),
  ADD CONSTRAINT `FKrs9s542i0a7nen8f9vfheehv9` FOREIGN KEY (`competitions_id`) REFERENCES `competition` (`id`);

--
-- Constraints for table `competition_participated_members`
--
ALTER TABLE `competition_participated_members`
  ADD CONSTRAINT `FKriw5jwrl0i9bj57vdkp8us4o7` FOREIGN KEY (`competitions_id`) REFERENCES `competition` (`id`),
  ADD CONSTRAINT `FKt9g3gnyxv4kjkvpm2g578xavx` FOREIGN KEY (`participated_members_id`) REFERENCES `member` (`id`);

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `FK5g9ub0a63rlx0ubh77i3gt2jr` FOREIGN KEY (`red_player_id`) REFERENCES `member` (`id`),
  ADD CONSTRAINT `FKfe3yl5sr1yofrbqdpeethdtm6` FOREIGN KEY (`blue_player_id`) REFERENCES `member` (`id`),
  ADD CONSTRAINT `FKqdsihqr13n8mhhci86rvjnken` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`);

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `FKlb5erj72ba5omm5wpkrfwvngr` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`);

--
-- Constraints for table `member_registrations`
--
ALTER TABLE `member_registrations`
  ADD CONSTRAINT `FKj4o3eplhvmfr97wratm8jrxxq` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  ADD CONSTRAINT `FKnfeyrtsdmatb50f9uo7mx4b4o` FOREIGN KEY (`registrations_id`) REFERENCES `registration` (`id`);

--
-- Constraints for table `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `FK9fk3n3ncmwvguy8ex9y46ymud` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`),
  ADD CONSTRAINT `FKgjv7ylejhs7by5b81rq3dlruo` FOREIGN KEY (`participant_id`) REFERENCES `member` (`id`);

--
-- Constraints for table `registration_competitions`
--
ALTER TABLE `registration_competitions`
  ADD CONSTRAINT `FKd0vfph3i287i74k240i3tor5k` FOREIGN KEY (`registrations_id`) REFERENCES `registration` (`id`),
  ADD CONSTRAINT `FKkxpngxeiiidronei15f8bg14n` FOREIGN KEY (`competitions_id`) REFERENCES `competition` (`id`);

--
-- Constraints for table `round`
--
ALTER TABLE `round`
  ADD CONSTRAINT `FK3iae81a1e63i7clqi5fdkmwf3` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`);

--
-- Constraints for table `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `FKpp6i5qd5su7jye4dgrw2ot213` FOREIGN KEY (`round_id`) REFERENCES `round` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKfhg6g8mbl6gcwupy69tajpvjm` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKbaw4yx3a33io4q601i88bqpkv` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

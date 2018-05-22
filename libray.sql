-- Host: 127.0.0.1
-- Czas generowania: 22 Maj 2018, 16:48
-- Wersja serwera: 10.1.31-MariaDB
-- Wersja PHP: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Baza danych: `libray`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admins`
--

CREATE TABLE `admins` (
  `adminId` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `permissionId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `admins`
--

INSERT INTO `admins` (`adminId`, `login`, `password`, `permissionId`) VALUES
(1, 'adminl', 'adminh', '1'),
(2, 'adm', 'adm', '1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `borrows`
--

CREATE TABLE `borrows` (
  `borrowId` int(8) NOT NULL,
  `studentId` int(8) NOT NULL,
  `objectId` int(8) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `borrows`
--

INSERT INTO `borrows` (`borrowId`, `studentId`, `objectId`, `date`) VALUES
(1, 1, 10, '2018-04-22 21:46:48'),
(2, 1, 1, '2018-05-08 08:20:51'),
(3, 1, 1, '2018-05-08 08:25:05'),
(4, 1, 1, '2018-05-08 08:25:26'),
(5, 1, 1, '2018-05-08 17:54:32'),
(6, 1, 1, '2018-05-11 12:22:38'),
(7, 1, 1, '2018-05-11 12:46:31'),
(8, 2, 62, '2018-05-12 12:51:26'),
(9, 2, 63, '2018-05-12 12:52:01'),
(10, 2, 64, '2018-05-12 12:52:05'),
(11, 4, 62, '2018-05-13 07:37:01'),
(12, 4, 63, '2018-05-13 07:54:33'),
(13, 4, 64, '2018-05-13 07:58:19'),
(14, 4, 62, '2018-05-13 08:01:17'),
(15, 4, 63, '2018-05-13 08:02:55'),
(16, 4, 64, '2018-05-13 08:06:16'),
(17, 4, 2, '2018-05-13 11:15:31'),
(18, 4, 20, '2018-05-13 11:15:37'),
(19, 4, 62, '2018-05-13 11:26:52'),
(20, 4, 63, '2018-05-13 11:29:22'),
(21, 4, 20, '2018-05-13 12:43:04'),
(22, 4, 30, '2018-05-13 12:43:10'),
(23, 1, 1, '2018-05-13 13:50:45'),
(24, 4, 20, '2018-05-13 14:21:16'),
(25, 4, 62, '2018-05-13 14:21:28'),
(26, 4, 39, '2018-05-13 14:43:17'),
(27, 2, 62, '2018-05-13 14:46:00'),
(28, 1, 1, '2018-05-13 15:10:01');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `object`
--

CREATE TABLE `object` (
  `objectId` int(11) NOT NULL,
  `type` enum('Book','Film','Indefinite') CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `tytul` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `yearOfIssue` int(11) NOT NULL,
  `ability` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `object`
--

INSERT INTO `object` (`objectId`, `type`, `author`, `tytul`, `yearOfIssue`, `ability`) VALUES
(1, 'Indefinite', 'example author', 'example title', 0, 0),
(20, 'Indefinite', 'aaaa', 'aaa', 0, 0),
(53, 'Book', 'Adam Mickiewicz', 'Pan Tadeusz', 1990, 0),
(62, 'Book', 'Makarena', 'CocodecoMoreno', 2010, 0),
(63, 'Film', 'nikolas', 'z makarena wsrod gwiazk', 2018, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `students`
--

CREATE TABLE `students` (
  `studentId` int(11) NOT NULL,
  `login` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `permissionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `index` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `numberBooks` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `students`
--

INSERT INTO `students` (`studentId`, `login`, `haslo`, `permissionId`, `index`, `numberBooks`) VALUES
(1, 'example student', 'example password', '100', '999999', 1),
(2, 'eeeee', 'co', '123', '123456', 1),
(3, 'daniel', 'macielecki', '2', '999999', 0),
(4, '2', '2', '2', '3', 1),
(30, 'nowy', 'std', '1000', '1000', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminId`);

--
-- Indeksy dla tabeli `borrows`
--
ALTER TABLE `borrows`
  ADD PRIMARY KEY (`borrowId`);

--
-- Indeksy dla tabeli `object`
--
ALTER TABLE `object`
  ADD PRIMARY KEY (`objectId`);

--
-- Indeksy dla tabeli `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `admins`
--
ALTER TABLE `admins`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `borrows`
--
ALTER TABLE `borrows`
  MODIFY `borrowId` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT dla tabeli `object`
--
ALTER TABLE `object`
  MODIFY `objectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT dla tabeli `students`
--
ALTER TABLE `students`
  MODIFY `studentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;
COMMIT;


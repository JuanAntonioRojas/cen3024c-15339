CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `First` varchar(50) NOT NULL,
  `Middl` varchar(50) NOT NULL,
  `LastN` varchar(50) NOT NULL,
  `Photo` varchar(50) NOT NULL,
  `Phon1` varchar(20) NOT NULL,
  `Phon2` varchar(20) NOT NULL,
  `Addrs` varchar(100) NOT NULL,
  `City` varchar(50) NOT NULL,
  `State` varchar(10) NOT NULL,
  `ZipCd` varchar(10) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `SpMon` varchar(3) NOT NULL,
  `SpDay` varchar(3) NOT NULL,
  `YearB` int(10) NOT NULL,
  `Gendr` varchar(10) NOT NULL,
  `Marit` varchar(50) NOT NULL,
  `Paswd` varchar(50) NOT NULL,
  `Confr` varchar(50) NOT NULL,
  `Notif` varchar(50) NOT NULL,
  `Notes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `Title`, `First`, `Middl`, `LastN`, `Photo`, `Phon1`, `Phon2`, `Addrs`, `City`, `State`, `ZipCd`, `Email`, `SpMon`, `SpDay`, `YearB`, `Gendr`, `Marit`, `Paswd`, `Confr`, `Notif`, `Notes`) VALUES
(1, '', 'Juan', 'A', 'Rojas', '', '4078440007', '', '2525 Hiawatha Ave', 'Sanford', 'FL', '32773', 'tonyrojas007@gmail.com', 'Mar', '26', 1960, 'Male', '', 'Tr@6093007', 'Tr@6093007', '', 'Nothing to declare'),
(2, '', 'Tony', '', 'Rojas', '', '4078440007', '', '2525 Hiawatha Ave', 'Sanford', 'FL', '32773', 'tonyrojas007', 'Mar', '26', 1960, 'Male', '', 'Tr@6093007', 'Tr@6093007', '', 'Nothing to declare'),
(3, '', 'Dennis', 'M', 'Ritchie', '', '908-647-4321', '998-582-3275', '132 Sherman Ave', 'Berkeley Heights', 'NJ', '79222', 'DMacAlistairRitchie@att.com', 'Sep', '9', 1940, 'Male', '', '100', '100', '', 'Inventor of the C language'),
(4, '', 'Kenneth', 'L', 'Thompson ', '', '908-604-1234', '998-456-4567 ', '600 Mountain Ave. Bldg 5', 'New Providence', 'NJ', '79072', 'KenLaneThompson@google.com', 'Feb', '4', 1943, 'Male', '', '101', '101', '', 'Genius that invented Unix'),
(5, '', 'Captain ', 'J', 'Sparrow ', '', '999-555-1212', '123-456-4567', '555 Johnny Depp Ave', 'Hollywood ', 'CA ', '90048', 'CaptainJack@gmail.com', 'Jun', '9', 1963, 'Male', '', 'abc', 'abc', '', 'Drunken pirate w good sense of humer and manners'),
(6, '', 'john ', '', 'doe', '', '3216-54', '3216-54', '123 Any St', 'Orlando ', 'FL', '32801', 'tonyrojas007@gmail.com', 'Jun', '15', 1960, 'Female', '', '654654', '654654', '', 'Neverland and grew up to be a grandmother.'),
(7, 'Capt.', 'James', '', 'Hook', '', '340-227-0918', '340-643-3427', '32 Red Hook Rt', 'St Thomas', 'US Virgin ', '99999', 'captainHook@AOL.com', 'Jan', '1', 1890, 'null', 'null', '555', '555', 'null', 'Old pirate, who hates Peter Pan'),
(8, 'Ms.', 'Mary', '', 'Poppins', '', '407-123-4567', '456-789-1234', '123 Fancy Road', 'London', 'UK', 'WS3 4TP', 'marypoppins@gmail.com', 'Jun', '16', 1955, 'null', 'null', '123', '123', 'null', 'She lived with the Banks family at 17 Cherry Tree Ln.'),
(9, 'Ms.', 'Wendy', 'M', 'Darling', '', '440-201-2345', '440-201-2345', '18 Holland Park - Kensington', 'London', 'UK', 'W8 4PT', 'WendyMoiraDarling@AOL.com', 'Jun', '20', 1920, 'Female', 'null', '7777', '7777', 'null', 'Finally left Neverland and grew up to be a grandmother.');

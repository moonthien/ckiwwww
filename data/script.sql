-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.36 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for theloaialbum
CREATE DATABASE IF NOT EXISTS `theloaialbum` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `theloaialbum`;

-- Dumping structure for table theloaialbum.album
CREATE TABLE IF NOT EXISTS `album` (
  `gia` int NOT NULL,
  `maal` int NOT NULL AUTO_INCREMENT,
  `matheloai` int NOT NULL,
  `namphathanh` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `tenal` varchar(255) NOT NULL,
  `trangthai` varchar(255) NOT NULL,
  PRIMARY KEY (`maal`),
  KEY `FKcbqhuit49ldaawbd5qj40toj4` (`matheloai`),
  CONSTRAINT `FKcbqhuit49ldaawbd5qj40toj4` FOREIGN KEY (`matheloai`) REFERENCES `theloai` (`matheloai`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table theloaialbum.album: ~5 rows (approximately)
INSERT INTO `album` (`gia`, `maal`, `matheloai`, `namphathanh`, `description`, `hinhanh`, `tenal`, `trangthai`) VALUES
	(40000, 1, 1, '2024-11-03', 'Đánh đổi Obito', '/uploads/Gc9m9AtbQAA67Ie.jpeg', 'Đánh đổi', 'Đang phát hành'),
	(49998, 2, 1, '2024-11-24', 'ACPBDTDD của hái thứ hiêu', '/uploads/1732303426285_462569655_3975034509441462_7985018139890831799_n.png', 'ACPBĐTDD', 'Ngưng phát hành'),
	(100000, 3, 1, '2024-11-10', '99% của MCK', '/uploads/1732303696325_445373017_7625831847511173_8766972260955290428_n.jpg', '99%', 'Đang phát hành'),
	(33111, 4, 1, '2024-11-14', 'test aaabbcccc', '/uploads/1732304925861_467696685_3862860017291609_7211354914085330805_n.jpg', 'test 132', 'Ngưng phát hành'),
	(12031, 5, 2, '2024-11-12', 'dddrrrqqqeee', '/uploads/1732305889300_465226281_122140354382351806_3992142758320897544_n.jpg', 'aa trqw', 'Đang phát hành');

-- Dumping structure for table theloaialbum.theloai
CREATE TABLE IF NOT EXISTS `theloai` (
  `matheloai` int NOT NULL AUTO_INCREMENT,
  `mota` varchar(255) DEFAULT NULL,
  `tentheloai` varchar(255) NOT NULL,
  PRIMARY KEY (`matheloai`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table theloaialbum.theloai: ~2 rows (approximately)
INSERT INTO `theloai` (`matheloai`, `mota`, `tentheloai`) VALUES
	(1, 'Nhạc Rap', 'Rap/Hiphop'),
	(2, 'Nhạc Ballad', 'Ballad');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

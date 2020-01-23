
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pname` varchar(100) NOT NULL,
  `price` int(15) NOT NULL,
  `link` varchar(30) NOT NULL,
  `fimage` varchar(20) NOT NULL,
  `simage` varchar(20) NOT NULL,
  `videos` int(5) NOT NULL,
  `clicks` int(20) NOT NULL,
  `des` varchar(100) NOT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


INSERT INTO `product` (`id`, `pname`, `price`,`link`, `fimage`, `simage`,`videos`,`clicks`,`des` ) VALUES
  (1, 'samsung', 50000 ,'samA1',"6.jpg", "6.jpg",5,0,'boss'),
  (2, 'lg', 30000 ,'lgA1',"6.jpg", "6.jpg",5,0,'best');
  






DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(60) NOT NULL,
  `message` varchar(330) NOT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


INSERT INTO `message` (`id`, `username`, `email`,`message`) VALUES
  (1, 'ayan basak', 'abc@gmail.com','that tv has nice performence'),
  (2, 'shovon', 'sh@gmail.com','i feel like i am in the haven');
  
  
  
  
DROP TABLE IF EXISTS `emails`;
CREATE TABLE IF NOT EXISTS `emails` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;



INSERT INTO `emails` (`id`, `email`) VALUES
  (1, 'abc@gmail.com'),
  (2, 'sh@gmail.com');
  
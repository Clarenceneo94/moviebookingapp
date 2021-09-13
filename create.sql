Create schema moviebookingschema;

use moviebookingschema;

CREATE TABLE `movie` (
  `movie_id` bigint NOT NULL AUTO_INCREMENT,
  `movie_desc` varchar(255) DEFAULT NULL,
  `movie_name` varchar(255) DEFAULT NULL,
  `movie_show_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



CREATE TABLE `seat` (
  `seat_id` bigint NOT NULL AUTO_INCREMENT,
  `seat_number` int DEFAULT NULL,
  `seat_price` bigint DEFAULT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `movie_id` bigint NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FKkr1r163u7isc9cdmg6p4fs8wk` (`movie_id`),
  CONSTRAINT `FKkr1r163u7isc9cdmg6p4fs8wk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;


CREATE TABLE `booking` (
  `booking_id` bigint NOT NULL AUTO_INCREMENT,
  `booking_status` varchar(255) DEFAULT NULL,
  `booking_time` varchar(255) DEFAULT NULL,
  `seat_id` bigint DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `UK_qooel92aivqm07s3md6vpbsj1` (`seat_id`),
  CONSTRAINT `FK7ryitbom1ln9okwlj2t9tt9ym` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

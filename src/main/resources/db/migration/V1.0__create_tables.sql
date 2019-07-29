CREATE TABLE `customer` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL
);

CREATE TABLE `channel` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `number` int NOT NULL,
  `name` varchar(255) NOT NULL
);

CREATE INDEX idx_channel_number ON channel(number);

CREATE TABLE `customer_channel` (
  `customer_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL,
  `subscribed` bit DEFAULT FALSE,
  PRIMARY KEY (`customer_id`,`channel_id`),
  CONSTRAINT `channel_belongs_to_customer` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`) ON DELETE CASCADE,
  CONSTRAINT `customer_has_channel` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE
);



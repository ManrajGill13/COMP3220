CREATE TABLE `schools`
(
    `id`      int(11) unsigned NOT NULL AUTO_INCREMENT,
    `x_coord` double(11, 8)    NOT NULL,
    `y_coord` double(11, 8)    NOT NULL,
    `fid`     int(11)          NOT NULL,
    `name`    varchar(255)     NOT NULL,
    `address` varchar(255)     NOT NULL,
    `board`   varchar(255)     NOT NULL,
    PRIMARY KEY (`id`)
)

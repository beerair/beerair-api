CREATE DATABASE beerair CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `beer`
(
    `id`          varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `alcohol`     float                                                         DEFAULT NULL,
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`   varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `kor_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `type_id`     bigint                                                        DEFAULT NULL,
    `volume`      int                                                           DEFAULT NULL,
    `price`       int                                                           DEFAULT NULL,
    `country_id`  bigint                                                        DEFAULT NULL,
    `deleted_at`  datetime                                                      DEFAULT NULL,
    `created_at`  datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON beer (id);

CREATE TABLE `beer_type`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `kor_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`   varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `deleted_at`  datetime                                                      DEFAULT NULL,
    `created_at`  datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON beer_type (id);

CREATE TABLE `review`
(
    `id`                   varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `previous_id`          varchar(50) COLLATE utf8mb4_general_ci                        DEFAULT NULL,
    `created_at`           datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`           datetime                                                      DEFAULT NULL,
    `modified_at`          datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `content`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `departure_country_id` bigint                                                        DEFAULT NULL,
    `arrival_country_id`   bigint                                                        DEFAULT NULL,
    `feel_status`          varchar(255) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `image_url`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `is_public`            bit(1)                                                        DEFAULT NULL,
    `flavor1`              bigint                                                        DEFAULT NULL,
    `flavor2`              bigint                                                        DEFAULT NULL,
    `flavor3`              bigint                                                        DEFAULT NULL,
    `beer_id`              varchar(50) COLLATE utf8mb4_general_ci                        DEFAULT NULL,
    `member_id`            varchar(50) COLLATE utf8mb4_general_ci                        DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON review (id);
CREATE INDEX idx_previous_id ON review (previous_id);
CREATE INDEX idx_beer_id ON review (beer_id);
CREATE INDEX idx_member_id ON review (member_id);

CREATE TABLE `flavor`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`  datetime                                                      DEFAULT NULL,
    `modified_at` datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON flavor (id);

CREATE TABLE `member`
(
    `id`           varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `created_at`   datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`   datetime                                                      DEFAULT NULL,
    `modified_at`  datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `email`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `nickname`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
    `profile_url`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `role`         varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   DEFAULT NULL,
    `social_id`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `social_type`  varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   DEFAULT NULL,
    `exp`          int                                                           DEFAULT NULL,
    `level_id`     bigint                                                        DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON member (id);

CREATE TABLE `level`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`  datetime                                                      DEFAULT NULL,
    `modified_at` datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `image_url`   varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `exp`         int                                                           DEFAULT NULL,
    `tier`        int                                                           DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `continent`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `kor_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `eng_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `deleted_at`  datetime                                                     DEFAULT NULL,
    `created_at`  datetime                                                     DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime                                                     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON continent (id);

CREATE TABLE `country`
(
    `id`                   bigint NOT NULL AUTO_INCREMENT,
    `background_image_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `kor_name`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `continent_id`         bigint                                                        DEFAULT NULL,
    `created_at`           datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`           datetime                                                      DEFAULT NULL,
    `modified_at`          datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON country (id);
CREATE INDEX idx_kor_name ON country (kor_name);
CREATE INDEX idx_eng_name ON country (eng_name);

CREATE TABLE `beer_like`
(
    `id`          varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`  datetime DEFAULT NULL,
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `beer_id`     varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `member_id`   varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
CREATE INDEX idx_id ON beer_like (id);
CREATE INDEX idx_member_id ON beer_like (member_id);

CREATE TABLE `suggest`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `created_at`    datetime                                DEFAULT NULL,
    `deleted_at`    datetime                                DEFAULT NULL,
    `modified_at`   datetime                                DEFAULT NULL,
    `beer_name`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `completed_at`  datetime                                DEFAULT NULL,
    `image_urls`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `member_id`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `reject_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `status`        int                                     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `image_metadata`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT NULL,
    `deleted_at`  datetime                                DEFAULT NULL,
    `modified_at` datetime                                DEFAULT NULL,
    `filename`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`   varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `member_id`   varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `system_action_log`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT NULL,
    `deleted_at`  datetime                                DEFAULT NULL,
    `modified_at` datetime                                DEFAULT NULL,
    `host`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `http_method` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `ip_address`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `path`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `referer`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `user_agent`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

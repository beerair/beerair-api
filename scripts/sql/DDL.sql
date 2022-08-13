CREATE
DATABASE beerair CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 맥주 도메인
CREATE TABLE `beer`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `alcohol`    float                                   DEFAULT NULL,
    `content`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`  varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `kor_name`   varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`   varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `type_id`    bigint                                  DEFAULT NULL,
    `volume`     int                                     DEFAULT NULL,
    `price`      int                                     DEFAULT NULL,
    `country_id` bigint                                  DEFAULT NULL,
    `deleted_at` datetime                                DEFAULT NULL,
    `created_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 맥주 도메인
CREATE TABLE `beer_type`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `kor_name`   varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`   varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `content`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`  varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `deleted_at` datetime                                DEFAULT NULL,
    `created_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `review`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `created_at`       datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`       datetime                                DEFAULT NULL,
    `updated_at`       datetime                                DEFAULT CURRENT_TIMESTAMP,
    `content`          varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `start_country_id` bigint                                  DEFAULT NULL,
    `end_country_id`   bigint                                  DEFAULT NULL,
    `feel`             int                                     DEFAULT NULL,
    `image_url`        varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `is_public`        bit(1)                                  DEFAULT NULL,
    `flavor_ids`       varchar(16)                             DEFAULT NULL,
    `beer_id`          bigint                                  DEFAULT NULL,
    `member_id`        bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `flavor`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at` datetime                                DEFAULT NULL,
    `updated_at` datetime                                DEFAULT CURRENT_TIMESTAMP,
    `content`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `member`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `created_at`   datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`   datetime                                DEFAULT NULL,
    `updated_at`   datetime                                DEFAULT CURRENT_TIMESTAMP,
    `email`        varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `nickname`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `phone_number` varchar(16) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `profile_url`  varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `role`         varchar(8) COLLATE utf8mb4_general_ci   DEFAULT NULL,
    `social_id`    varchar(32) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `social_type`  varchar(8) COLLATE utf8mb4_general_ci   DEFAULT NULL,
    `exp`          int                                     DEFAULT NULL,
    `level_id`     bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `level`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`  datetime                                DEFAULT NULL,
    `updated_at`  datetime                                DEFAULT CURRENT_TIMESTAMP,
    `image_url`   varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `request_exp` int                                     DEFAULT NULL,
    `tier`        int                                     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 맥주 도메인
CREATE TABLE `continent`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `kor_name`   varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `eng_name`   varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `deleted_at` datetime                               DEFAULT NULL,
    `created_at` datetime                               DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime                               DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 맥주 도메인
CREATE TABLE `country`
(
    `id`                   bigint NOT NULL AUTO_INCREMENT,
    `background_image_url` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url`            varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `kor_name`             varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `eng_name`             varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `continent_id`         bigint                                  DEFAULT NULL,
    `created_at`           datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`           datetime                                DEFAULT NULL,
    `updated_at`           datetime                                DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 도메인 따로
CREATE TABLE `beer_like`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `deleted_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `beer_id`    bigint   DEFAULT NULL,
    `member_id`  bigint   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 도메인 따로
CREATE TABLE `beer_suggest`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `created_at`       datetime                                DEFAULT CURRENT_TIMESTAMP,
    `deleted_at`       datetime                                DEFAULT NULL,
    `updated_at`       datetime                                DEFAULT CURRENT_TIMESTAMP,
    `image_url_first`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `image_url_second` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `beer_name`        varchar(64) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `completed_at`     datetime                                DEFAULT NULL,
    `rejection_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `status`           varchar(16) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `member_id`        bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
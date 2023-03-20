CREATE TABLE `person`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(64) DEFAULT NULL,
    `male` tinyint     DEFAULT NULL,
    `age`  tinyint     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB  DEFAULT CHARSET = utf8;

CREATE TABLE `operate_log`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `operate` tinyint      DEFAULT NULL COMMENT '操作',
    `log`     varchar(256) DEFAULT NULL COMMENT '日志',
    `time`    timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB  DEFAULT CHARSET = utf8
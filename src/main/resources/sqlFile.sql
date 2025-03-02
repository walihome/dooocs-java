CREATE TABLE `ranking_items` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `list_id` BIGINT NOT NULL COMMENT '所属列表ID',
    `title` VARCHAR(255) NOT NULL COMMENT '标题',
    `description` TEXT COMMENT '描述',
    `rank` INT NOT NULL COMMENT '排名',
    `media_url` VARCHAR(1024) COMMENT '媒体URL',
    `update_cycle` VARCHAR(50) COMMENT '更新周期',
    `creator_id` BIGINT COMMENT '创建者ID',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_list_id` (`list_id`),
    INDEX `idx_rank` (`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排名项目表';

CREATE TABLE `ranking_lists` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL COMMENT '列表标题',
    `description` TEXT COMMENT '列表描述',
    `creator_id` BIGINT NOT NULL COMMENT '创建者ID',
    `status` VARCHAR(50) COMMENT '列表状态',
    `update_cycle` VARCHAR(50) COMMENT '更新周期',
    `cover_url` VARCHAR(1024) COMMENT '封面图片URL',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_creator_id` (`creator_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排名列表主表';

CREATE TABLE `user_collections` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `list_id` BIGINT NOT NULL COMMENT '列表ID',
    `type` VARCHAR(50) NOT NULL COMMENT '收藏类型',
    `status` VARCHAR(50) NOT NULL COMMENT '收藏状态',
    `update_cycle` VARCHAR(50) COMMENT '更新周期',
    `media_url` VARCHAR(1024) COMMENT '媒体URL',
    `creator_id` BIGINT COMMENT '创建者ID',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_list_id` (`list_id`),
    INDEX `idx_type` (`type`),
    UNIQUE KEY `uk_user_list_type` (`user_id`, `list_id`, `type`) COMMENT '用户收藏唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏表';

CREATE TABLE `user_follows` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `user_id` BIGINT NOT NULL COMMENT '关注者ID',
    `target_id` BIGINT NOT NULL COMMENT '被关注者ID',
    `status` VARCHAR(50) NOT NULL COMMENT '关注状态',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_target_id` (`target_id`),
    UNIQUE KEY `uk_user_target` (`user_id`, `target_id`) COMMENT '用户关注唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户关注关系表';


CREATE TABLE `user_likes` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `target_id` BIGINT NOT NULL COMMENT '目标ID',
    `target_type` VARCHAR(50) NOT NULL COMMENT '目标类型',
    `ip` VARCHAR(50) COMMENT '操作IP',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_target_id` (`target_id`),
    INDEX `idx_target_type` (`target_type`),
    UNIQUE KEY `uk_user_target` (`user_id`, `target_id`, `target_type`) COMMENT '用户点赞唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户点赞表';

CREATE TABLE `users` (
    `id` BIGINT NOT NULL PRIMARY KEY,
    `username` VARCHAR(100) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `email` VARCHAR(255) NOT NULL COMMENT '邮箱',
    `avatar` VARCHAR(1024) COMMENT '头像URL',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uk_username` (`username`) COMMENT '用户名唯一索引',
    UNIQUE KEY `uk_email` (`email`) COMMENT '邮箱唯一索引',
    INDEX `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID，主键，自增',
    username      VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名，唯一标识',
    password      VARCHAR(255) NOT NULL COMMENT '密码，加密存储',
    email         VARCHAR(255) NOT NULL UNIQUE COMMENT '电子邮件，加密存储',
    phone         VARCHAR(255) NOT NULL UNIQUE COMMENT '手机号码，加密存储',
    id_card       VARCHAR(255) UNIQUE COMMENT '身份证号，加密存储',
    name          VARCHAR(50) COMMENT '姓名',
    gender        TINYINT(1) COMMENT '性别，0: 女, 1: 男',
    date_of_birth DATE COMMENT '出生日期',
    created_at    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    last_login    TIMESTAMP COMMENT '最后登录时间',
    status        TINYINT(1) DEFAULT 0 COMMENT '用户状态，0: 未激活, 1: 激活, 2: 锁定',
    deleted       TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志，0: 未删除, 1: 已删除',
    INDEX idx_username (username) COMMENT '用户名索引',
    INDEX idx_status (status) COMMENT '用户状态索引',
    INDEX idx_deleted (deleted) COMMENT '逻辑删除标志索引'
) COMMENT ='用户表，存储用户基本信息';
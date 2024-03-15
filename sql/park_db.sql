-- auto-generated definition
create schema park_db collate utf8mb4_general_ci;


use park_db;

-- auto-generated definition
create table p_user_record_2
(
    id       bigint auto_increment primary key,
    username varchar(256) default '' not null comment '账号',
    password varchar(256) default '' not null comment '密码'

)
    collate = utf8mb4_unicode_ci;


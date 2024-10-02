create table refresh_token
(
    token_id        serial
        primary key,
    expiration_time timestamp(6) with time zone not null,
    refresh_token   varchar(500)                not null,
    user_user_id    integer
        constraint uk_mw99w2d9yrljeaowdl0siv3e3
            unique
        constraint fkjpmlw49x98wb3sfpca2n03men
            references users
);

alter table refresh_token
    owner to portal;


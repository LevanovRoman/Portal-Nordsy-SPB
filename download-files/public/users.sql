create table users
(
    user_id  serial
        primary key,
    email    varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    name     varchar(255),
    password varchar(255),
    role     varchar(255)
        constraint users_role_check
            check ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    username varchar(255)
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to portal;


create table chapter
(
    id   bigint generated by default as identity
        primary key,
    name varchar(255)
);

alter table chapter
    owner to portal;


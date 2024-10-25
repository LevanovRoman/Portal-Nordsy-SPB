create table result5s
(
    id           bigint generated by default as identity
        primary key,
    value        integer not null,
    area_id      bigint
        constraint fk9epwlomn8hh0m1tjx92tjc7d7
            references area5s,
    criterion_id bigint
        constraint fkihjuvua6cpfcwm6ems40mdj3n
            references criterion5s,
    month_id     bigint
        constraint fknrhpmmtsie9gk1kexipjipl54
            references month5s
);

alter table result5s
    owner to portal;


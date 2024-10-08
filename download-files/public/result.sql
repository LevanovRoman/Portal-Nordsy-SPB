create table result
(
    id           bigint generated by default as identity
        primary key,
    value        integer not null,
    area_id      bigint
        constraint fkmuvmv2s4l3tv026inm8dr25ik
            references area,
    criterion_id bigint
        constraint fkhh6v1g2hoquqrauq1fpnwenub
            references criterion,
    week_id      bigint
        constraint fkqbiuvfxh36c1q4guq56ekq6vi
            references week
);

alter table result
    owner to portal;


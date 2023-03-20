create table public.t_poi
(
    id      serial
        primary key,
    name    varchar(255) not null,
    address varchar(255),
    geom    geometry(Point, 4326)
);

alter table public.t_poi
    owner to postgres;
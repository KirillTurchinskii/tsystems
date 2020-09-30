create table if not exists train
(
    id       serial not null,
    capacity integer,
    type     varchar(40),
    number   varchar(40),
    velocity integer,
    constraint trains_pkey
        primary key (id),
    constraint trains_number_key
        unique (number)
);

alter table train
    owner to postgres;

create table if not exists passenger
(
    id         serial      not null,
    name       varchar(40),
    surname    varchar(40),
    birth_date date,
    username   varchar(40) not null,
    email      varchar(50),
    constraint passengers_pkey
        primary key (id),
    constraint passenger_username_key
        unique (username),
    constraint passenger_email_key
        unique (email)
);

alter table passenger
    owner to postgres;

create table if not exists schedule
(
);

alter table schedule
    owner to postgres;

create table if not exists test
(
);

alter table test
    owner to postgres;

create table if not exists tickets
(
);

alter table tickets
    owner to postgres;



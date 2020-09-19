create table test
(
    id     serial not null
        constraint test_pkey
            primary key,
    text   varchar(255),
    number integer
);

alter table test
    owner to postgres;

create table trains
(
    id       serial not null
        constraint trains_pkey
            primary key,
    name     varchar(40),
    capacity integer
);

alter table trains
    owner to postgres;

create table schedule
(
    id       integer not null
        constraint schedule_pkey
            primary key
        constraint schedule_id_key
            unique,
    train_id integer
        constraint schedule_train_id_fkey
            references trains,
    time     timestamp
);

alter table schedule
    owner to postgres;

create table passengers
(
    id         serial not null
        constraint passengers_pkey
            primary key,
    name       varchar(40),
    surname    varchar(40),
    birth_date date
);

alter table passengers
    owner to postgres;

create table tickets
(
    id           serial not null
        constraint tickets_pkey
            primary key,
    passenger_id integer
        constraint tickets_passenger_id_fkey
            references passengers
);

alter table tickets
    owner to postgres;



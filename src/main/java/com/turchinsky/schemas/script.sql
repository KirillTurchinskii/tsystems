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

create table if not exists route
(
    id   serial not null,
    name varchar(50),
    constraint routes_pkey
        primary key (id)
);

alter table route
    owner to postgres;

create table if not exists station
(
    id   serial not null,
    name varchar(50),
    constraint stations_pkey
        primary key (id),
    constraint station_name_key
        unique (name)
);

alter table station
    owner to postgres;

create table if not exists train_has_schedule_and_route
(
    train_id       integer   not null,
    route_id       integer   not null,
    departure_time timestamp not null,
    ordered_seats  integer,
    free_seats     integer,
    constraint train_has_schedule_pk
        primary key (train_id, route_id, departure_time),
    constraint train_schedule_train_id_fkey
        foreign key (train_id) references train,
    constraint train_has_schedule_and_route_route_id_fkey
        foreign key (route_id) references route
);

alter table train_has_schedule_and_route
    owner to postgres;

create table if not exists route_details
(
    route_id      integer not null,
    station_id    integer not null,
    station_order integer not null,
    km            integer,
    type          integer,
    constraint route_details_pkey
        primary key (route_id, station_id, station_order),
    constraint route_details_route_id_fkey
        foreign key (route_id) references route,
    constraint route_details_station_id_fkey
        foreign key (station_id) references station
);

alter table route_details
    owner to postgres;

create table if not exists schedule_details
(
    route_id       integer,
    station_id     integer,
    station_order  integer,
    train_id       integer,
    line_id        serial not null,
    arrival_time   timestamp,
    departure_time timestamp,
    free_seats     integer,
    ordered_seats  integer,
    constraint schedule_details_pkey
        primary key (line_id),
    constraint route_details_fk
        foreign key (route_id, station_id, station_order) references route_details,
    constraint train_id_fk
        foreign key (train_id) references train
);

alter table schedule_details
    owner to postgres;

create table if not exists ticket
(
    id           serial not null,
    price        integer,
    station_from integer,
    station_to   integer,
    line_id      integer,
    constraint ticket_pkey
        primary key (id),
    constraint ticket_station_from_fkey
        foreign key (station_from) references station,
    constraint ticket_station_to_fkey
        foreign key (station_to) references station,
    constraint ticket_line_id_fkey
        foreign key (line_id) references schedule_details
);

alter table ticket
    owner to postgres;

create table if not exists passenger_has_ticket
(
    passenger_id integer,
    ticket_id    integer,
    constraint passenger_ticket_passenger_id_fkey
        foreign key (passenger_id) references passenger,
    constraint passenger_ticket_ticket_id_fkey
        foreign key (ticket_id) references ticket
);

alter table passenger_has_ticket
    owner to postgres;



select train_id, arrival_time, departure_time
from schedule_details
where route_id in (select id
                   from route
                   where exists(select *
                                from route_details
                                where route_details.route_id = id
                                  and route_details.station_id = 3
                                  and exists(select *
                                             from route_details rd2
                                             where rd2.route_id = route_details.route_id
                                               and rd2.station_id = 4)))

  and arrival_time between '2020.09.30 15:00:00' and '2020.09.30 16:00:00'
  and (station_id = 3 or station_id = 4);

select *
from schedule_details
where station_id = 3
order by arrival_time;


select *
from route_details
where route_details.route_id = 1
  and route_details.station_id = 4;


select *
from route_details
where route_details.route_id = 1
  and route_details.station_id = 1
  and exists(select *
             from route_details rd2
             where rd2.route_id = route_details.route_id
               and rd2.station_id = 4);



alter table passenger
    add unique (email);



select *
from train;
-- 22 23 24
select *
from route_details;

select *
from schedule_details;

insert into schedule_details (route_id, station_id, station_order, train_id, arrival_time, departure_time)
VALUES (3, 4, 2, 24, '2020-09-30 14:12:00', '2020-09-30 14:15:00');

alter table train_has_schedule_and_route
    add constraint train_has_schedule_pk primary key (train_id, route_id, departure_time);

alter table schedule_details
    add column ordered_seats integer;

alter table ticket
    add column line_id integer references schedule_details (line_id);

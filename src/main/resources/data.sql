/**
 * CREATE Script for init of DB
 */

-- Create 3 cars for ONLINE drivers
insert into car (id, date_created, model, manufacturer, license_plate, seat_count, convertible, rating, engine_type, car_in_use, deleted)
values (1, now(), 'Golf 1.4 TDI', 'Volkswagen', '34AB1250', 4, false, 4, 'DIESEL', true, false);

insert into car (id, date_created, model, manufacturer, license_plate, seat_count, convertible, rating, engine_type, car_in_use, deleted)
values (2, now(), 'Corsa 1.2', 'Opel', '35YT332', 4, false, 3, 'GAS', true, false);

insert into car (id, date_created, model, manufacturer, license_plate, seat_count, convertible, rating, engine_type, car_in_use, deleted)
values (3, now(), '4.20d', 'BMW', '01LG2301', 2, true, 5, 'ELECTRIC', true, false);

-- Create 1 car that is not being used by any driver

insert into car (id, date_created, model, manufacturer, license_plate, seat_count, convertible, rating, engine_type, car_in_use, deleted)
values (4, now(), 'Punto 1.0 Multijet', 'Fiat', '55TY9980', 2, false, 2, 'GAS', false, false);


-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04', 1);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05', 2);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06', 3);

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');


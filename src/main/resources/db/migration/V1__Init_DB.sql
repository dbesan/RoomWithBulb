create sequence hibernate_sequence start 1 increment 1;
create table room
(
    id        int8         not null,
    room_name varchar(255) not null,
    status    int4         not null,
    country   varchar(255) not null,
    primary key (id)
);
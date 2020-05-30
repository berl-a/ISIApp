create table cars
(
    id      bigint auto_increment
        primary key,
    created datetime     null,
    status  varchar(255) null,
    updated datetime     null,
    brand   varchar(255) null,
    model   varchar(255) null
);


create table clients
(
    id         bigint auto_increment
        primary key,
    created    datetime     null,
    status     varchar(255) null,
    updated    datetime     null,
    first_name varchar(255) null,
    google_id  varchar(255) null,
    last_name  varchar(255) null
);


create table drivers
(
    id         bigint auto_increment
        primary key,
    created    datetime     null,
    status     varchar(255) null,
    updated    datetime     null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    driver     bigint       null,
    constraint FKn17pw9siphrwpv9w2e1a6f9pb
        foreign key (driver) references cars (id)
);

INSERT INTO isiapp.drivers (id, created, status, updated, first_name, last_name, driver) VALUES (1, null, 'ACTIVE', null, 'John', 'Johnson', null);
create table rides
(
    id              bigint auto_increment
        primary key,
    created         datetime     null,
    status          varchar(255) null,
    updated         datetime     null,
    destination_lat double       not null,
    destination_lon double       not null,
    origin_lat      double       not null,
    origin_lon      double       not null,
    price           double       not null,
    car             bigint       null,
    client          bigint       null,
    driver          bigint       null,
    constraint FK732xgvu787lqkqh1bv8hb3vlk
        foreign key (car) references cars (id),
    constraint FK99ny5ocym2sxomtx1gwnk7jtx
        foreign key (driver) references drivers (id),
    constraint FKejc14gct8aqn3yo7vuti6wciu
        foreign key (client) references clients (id)
);

INSERT INTO isiapp.rides (id, created, status, updated, destination_lat, destination_lon, origin_lat, origin_lon, price, car, client, driver) VALUES (1, null, 'ACTIVE', null, 12, 12, 15, 14, 10, null, null, null);
INSERT INTO isiapp.rides (id, created, status, updated, destination_lat, destination_lon, origin_lat, origin_lon, price, car, client, driver) VALUES (2, null, 'ACTIVE', null, 1, 23, 14, 4, 155, null, null, null);
INSERT INTO isiapp.rides (id, created, status, updated, destination_lat, destination_lon, origin_lat, origin_lon, price, car, client, driver) VALUES (3, null, 'ACTIVE', null, 144, 1, 13, 16, 25, null, null, null);
create table roles
(
    id      bigint auto_increment
        primary key,
    created datetime     null,
    status  varchar(255) null,
    updated datetime     null,
    name    varchar(255) null
);

INSERT INTO isiapp.roles (id, created, status, updated, name) VALUES (1, null, 'ACTIVE', null, 'ADMIN');
INSERT INTO isiapp.roles (id, created, status, updated, name) VALUES (2, null, 'ACTIVE', null, 'USER_APP');
INSERT INTO isiapp.roles (id, created, status, updated, name) VALUES (3, null, 'ACTIVE', null, 'DRIVER_APP');
create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id) references roles (id),
    constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id) references users (id)
);

INSERT INTO isiapp.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO isiapp.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO isiapp.user_roles (user_id, role_id) VALUES (3, 3);
create table users
(
    id       bigint auto_increment
        primary key,
    created  datetime     null,
    status   varchar(255) null,
    updated  datetime     null,
    password varchar(255) null,
    username varchar(255) null
);

INSERT INTO isiapp.users (id, created, status, updated, password, username) VALUES (1, null, 'ACTIVE', null, '$2a$04$Hv/npCF.beaBK43pDE/cLOUISnx./xNbEZJ6aOwskmcsvtfunlECu', 'admin');
INSERT INTO isiapp.users (id, created, status, updated, password, username) VALUES (2, null, 'ACTIVE', null, '$2a$04$hOk6AZ8Z.hjZ6NjdBV3Ij.WOJvYIEQCIzT0.LBwWaQMgHPK5IYABC', 'user_app_user');
INSERT INTO isiapp.users (id, created, status, updated, password, username) VALUES (3, null, 'ACTIVE', null, '$2a$04$kOke/PY00ikR9dka3c1f.eyCLv1yfCTL6yyJV50Y5ptMx1NLSuZMC', 'driver_app_user');
DROP TABLE IF EXISTS user;
DROP SEQUENCE if exists hibernate_sequence;
CREATE SEQUENCE hibernate_sequence start with 1 increment by 1;
CREATE TABLE user (
    id integer not null,
    email varchar(255),
    password varchar(255),
    role varchar(255),
    username varchar(255),
    primary key (id)
);
insert into user (email, password, role, username, id) values ('dev@gmail.com', 'dev123', 'admin', 'flyway',select nextval('hibernate_sequence'));

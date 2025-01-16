CREATE TABLE usuarios
(
    id BIGSERIAL not null,

    username varchar(100) not null,
    password varchar(300) not null unique,
    primary key (id)
);

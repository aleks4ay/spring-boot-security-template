create table if not exists users(
    username varchar(255) not null primary key,
    password varchar(255) not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar(255) not null,
    authority varchar(255) not null,
    foreign key (username) references users(username),
    unique (username, authority)
);
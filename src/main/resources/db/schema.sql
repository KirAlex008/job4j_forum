CREATE TABLE IF NOT EXISTS authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

create table IF NOT EXISTS posts (
                       id serial primary key,
                       name varchar(2000),
                       description varchar(255),
                       created timestamp,
                       user_id int references users(id)
);

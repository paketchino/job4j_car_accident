CREATE TABLE IF NOT EXISTS authorities (
                             id serial primary key,
                             authority text NOT NULL unique
);
CREATE TABLE IF NOT EXISTS users (
                       id serial primary key,
                       username text NOT NULL,
                       password text NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN'));
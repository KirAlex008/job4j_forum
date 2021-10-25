insert into authorities(authority) values ('ROLE_USER');
insert into authorities(authority) values ('ROLE_ADMIN');
insert into "users" (username, password, enabled, authority_id) values ('user', '$2a$10$YU0VQz3.y3FLVPPGjoCdhufzLsiYmb1VTty8ARIlVHq7NYiUFYBQi', true,
        (select id from "authorities" where authority = 'ROLE_USER'));

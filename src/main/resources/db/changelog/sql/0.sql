create type release_type as enum ('LIVE', 'PTU');
create type body_type as enum ('STAR', 'PLANET', 'MOON');

create table if not exists game_version
(
    id          serial primary key not null,
    description text               not null,
    type        release_type       not null default 'LIVE',
    active      boolean            not null default true
);

create table if not exists system
(
    id         serial primary key not null,
    name       text               not null,
    version_id integer,
    active     boolean            not null default true,
    constraint version_fk foreign key (version_id) references game_version (id)
        on delete set null
);

create table if not exists body
(
    id         serial primary key not null,
    parent_id  integer,
    version_id integer,
    system_id  integer                     default null,
    type       body_type          not null,
    name       text               not null,
    radius     numeric            not null default 0,
    om_radius  numeric            not null default 0,
    active     boolean            not null default true,
    constraint parent_fk foreign key (parent_id) references body (id),
    constraint system_fk foreign key (system_id) references system (id),
    constraint version_fk foreign key (version_id) references game_version (id)
        on delete set null
);

create table if not exists location
(
    id         serial primary key not null,
    body_id    integer            not null,
    version_id integer,
    name       text               not null,
    x          numeric            not null,
    y          numeric            not null,
    z          numeric            not null,
    active     boolean            not null default true,
    constraint body_fk foreign key (body_id) references body (id),
    constraint version_fk foreign key (version_id) references game_version (id)
        on delete set null
);

insert into game_version (description, type)
values ('3.8.1-LIVE.4222088', 'LIVE');

insert into system (name, version_id)
VALUES ('Stanton', (select id from game_version where description = '3.8.1-LIVE.4222088'));

insert into body(name, type, parent_id, system_id, radius, om_radius, version_id)
values ('Stanton', 'STAR', default, (select id from system where name = 'Stanton'), 0, 0, (select id from game_version where description = '3.8.1-LIVE.4222088'));

insert into body(name, type, parent_id, system_id, radius, om_radius, version_id)
values ('Hurston', 'PLANET', (select id from body WHERE name = 'Stanton'), (select id from system where name = 'Stanton'), 1000, 1438, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Crusader', 'PLANET', (select id from body where name = 'Stanton'), (select id from system where name = 'Stanton'), 0, 0, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Arccorp', 'PLANET', (select id from body WHERE name = 'Stanton'), (select id from system where name = 'Stanton'), 800, 1151, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Microtech', 'PLANET', (select id from body WHERE name = 'Stanton'), (select id from system where name = 'Stanton'), 1000, 1439, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Delamar', 'PLANET', (select id from body WHERE name = 'Stanton'), (select id from system where name = 'Stanton'), 75, 118.9, (select id from game_version where description = '3.8.1-LIVE.4222088'));

insert into body(name, type, parent_id, system_id, radius, om_radius, version_id)
values ('Arial', 'MOON', (SELECT id from body WHERE name = 'Hurston'), (select id from system where name = 'Stanton'),  344.494, 501, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Aberdeen', 'MOON', (SELECT id from body WHERE name = 'Hurston'), (select id from system where name = 'Stanton'),  274, 402.7, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Magda', 'MOON', (SELECT id from body WHERE name = 'Hurston'), (select id from system where name = 'Stanton'),  340.826, 494.8, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Ita', 'MOON', (SELECT id from body WHERE name = 'Hurston'),  (select id from system where name = 'Stanton'), 325, 472.4, (select id from game_version where description = '3.8.1-LIVE.4222088')),

       ('Cellin', 'MOON', (SELECT id from body WHERE name = 'Crusader'), (select id from system where name = 'Stanton'),  260, 380, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Daymar', 'MOON', (SELECT id from body WHERE name = 'Crusader'), (select id from system where name = 'Stanton'),  295, 430.9, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Yela', 'MOON', (SELECT id from body WHERE name = 'Crusader'), (select id from system where name = 'Stanton'),  313, 454, (select id from game_version where description = '3.8.1-LIVE.4222088')),

       ('Lyria', 'MOON', (SELECT id from body WHERE name = 'Arccorp'), (select id from system where name = 'Stanton'),  223, 328.2, (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Wala', 'MOON', (SELECT id from body WHERE name = 'Arccorp'), (select id from system where name = 'Stanton'),  283, 413, (select id from game_version where description = '3.8.1-LIVE.4222088'));



insert into location (name, x, y, z, body_id, version_id)
VALUES ('Stash house', 212.07106778819872, 80.18736734767006, 230.73538496881548, (select id from body where name = 'Yela'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Jumptown', 254.67000099175814, -28.697137555634768, -216.66025429572286, (select id from body where name = 'Yela'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Stash house', 21.680699552457817, -292.7535354772664, 30.85030967081471, (select id from body where name = 'Daymar'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Samson & son''s salvage center', 30.30673401287349, -281.0354022937451, 0.08261779011316861, (select id from body where name = 'Wala'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Brio''s breaker yard', 123.08490890932069, 106.15986829664959, 120.81904676033025, (select id from body where name = 'Daymar'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Reclamation & Disposal Orinth', -338.8898694315602, 653.0512046193211, 677.7094104607676, (select id from body where name = 'Hurston'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('The Orphanage', 77.47733231150481, -90.4551659495304, 189.26071134867726, (select id from body where name = 'Lyria'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Paradise cove', 78.09150927848681, -189.19278898598222, -47.620074177938314, (select id from body where name = 'Lyria'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Stash house', -90.86016951522274, 174.93727477838056, 176.8079984625911, (select id from body where name = 'Cellin'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Cave 1', -78.17526872044772, 257.8442034112845, 54.84227890172407, (select id from body where name = 'Aberdeen'), (select id from game_version where description = '3.8.1-LIVE.4222088')),
       ('Javelin wreck', -102.44715707096744, -266.9947551215592, -69.19350193056357, (select id from body where name = 'Daymar'), (select id from game_version where description = '3.8.1-LIVE.4222088'));



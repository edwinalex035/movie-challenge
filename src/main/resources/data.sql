insert into mrole (id, name) values (1000, 'ADMIN');
insert into mrole (id, name) values (1001, 'USER');


insert into musers(name, username, password, role_id)
    values ('admin', 'admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 1000);
insert into musers(name, username, password, role_id)
    values ('user', 'user', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 1001);


INSERT INTO movie
(id, name, release_date, sypnosis, category, created_by, created_at)
VALUES(1001, 'Start Wars Episodio 1', '2000-01-01', 'Star wars episode 1', 'ACTION', 1, '2000-01-01 00:00:00');


create table mrole (
    id int not null primary key,
    name varchar(255)
);

create table musers (
    id int not null auto_increment,
    name varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    role_id int not null,
     primary key (id),
    FOREIGN KEY (role_id) REFERENCES mrole(id)
);

create table movie (
    id int not null auto_increment,
    name varchar(255) not null,
    release_date date not null,
    sypnosis varchar(255),
    category varchar(255),
    created_by int,
    created_at timestamp,
    primary key (id)
);

create table rate (
    id int not null auto_increment,
    rate int not null,
    movie_id int not null,
    created_by int,
    created_at timestamp,
    primary key (id),
    foreign key (movie_id) references movie(id)
);
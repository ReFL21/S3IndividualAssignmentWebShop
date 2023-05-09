




CREATE TABLE matches
(
    id int NOT NULL   AUTO_INCREMENT,
    first_team varchar(45) NOT NULL ,
    second_team varchar(45) NOT NULL ,
    date varchar(45) NOT NULL ,
    PRIMARY KEY (id)

);

create table ticket
(
    id       int auto_increment
        primary key,
    place    varchar(45) null,
    date     varchar(45) null,
    match_id int         null,
    price    int         null,
    quantity int         null,
    constraint match_id
        foreign key (match_id) references matches (id)
);

create index match_id_idx
    on ticket (match_id);
CREATE TABLE users
(
    id int NOT NULL   AUTO_INCREMENT,
    name varchar (20) NOT NULL ,
    username varchar (20) NOT NULL ,
    password varchar (50) NOT NULL ,
    email varchar (50) NOT NULL ,

    PRIMARY KEY (id),
    UNIQUE (username)

);



CREATE TABLE orders
(
    id int  NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL ,
    ticket_id int NOT NULL ,
    date varchar(45) NOT NULL ,
    quantity int NOT NULL ,
    price int NOT NULL ,
    PRIMARY KEY (id),

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);

create table user_role
(
    id      int auto_increment
        primary key,
    role    varchar(45) null,
    user_id int         null,
    constraint user_id_UNIQUE
        unique (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);


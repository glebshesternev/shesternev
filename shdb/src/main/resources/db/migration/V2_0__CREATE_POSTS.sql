create table posts (
                       id int,
                       author_id int NOT NULL,
                       title varchar(30) NOT NULL,
                       description varchar(255) NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (author_id) REFERENCES users (id)
);
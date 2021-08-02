create table comments (
                        id int,
                        author_id int NOT NULL,
                        post_id int NOT NULL,
                        message varchar(100) NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (author_id) REFERENCES users (id),
                        FOREIGN KEY (post_id) REFERENCES posts (id)
);
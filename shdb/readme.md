#DB scheme

##Tables:
    
- users
- posts
- comments

##Users

| Column | Type | Nullable | Unique | Description |
|----------------|----------------|----------------|----------------|----------------|
|id|INT|false|true|pk|
|name|varchar(20)|false|false|user name|
|email|varchar(50)|false|false|user email|
|password|varchar(15)|false|false|user pass|


##Posts

| Column | Type | Nullable | Unique | Description |
|----------------|----------------|----------------|----------------|----------------|
|id|INT|false|true|pk|
|author_id|INT|false|false|fk (user.id)|
|title|varchar(30)|false|false|post title|
|description|varchar(255)|false|false|post description|

##Comments

| Column | Type | Nullable | Unique | Description |
|----------------|----------------|----------------|----------------|----------------|
|id|INT|false|true|pk|
|author_id|INT|false|false|fk (user.id)|
|post_id|INT|false|false|fk (post.id)|
|message|varchar(100)|false|false|message|   
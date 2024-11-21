CREATE TABLE user_table(
user_id varchar(12) not null ,
password varchar(15) not null,
name varchar(12) not null,
point integer,
item_list varchar(100),
PRIMARY KEY(user_id)
);
create table item(
item_id long generated by default as identity,
filename varchar(40) not null,
point int,
upload_date date,
tags_str varchar(50),
primary key(item_id)
)
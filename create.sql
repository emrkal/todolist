create table activity (id bigint not null auto_increment, activity_name varchar(255) not null, description varchar(255) not null, end_date date not null, start_date date not null, status integer not null, user bigint, primary key (id))
create table comment (id bigint not null auto_increment, active bit not null, comment_date date not null, description varchar(255) not null, user bigint, primary key (id))
create table user (id bigint not null auto_increment, email varchar(255) not null, last_name varchar(255) not null, name varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
alter table activity add constraint FK773gsjp3fbjmeb8or0vfhelk0 foreign key (user) references user (id)
alter table comment add constraint FKlxlm2octv83r6g7kkfxc9iwbi foreign key (user) references user (id)

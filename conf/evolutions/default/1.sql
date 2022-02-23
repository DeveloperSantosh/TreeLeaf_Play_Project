# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table blog (
  title                         varchar(255) not null,
  content                       varchar(255),
  timestamp                     varchar(255),
  author_id                     integer,
  constraint pk_blog primary key (title)
);

create table comment (
  username                      varchar(255) not null,
  comment                       varchar(255),
  timestamp                     varchar(255),
  constraint pk_comment primary key (username)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255),
  surname                       varchar(255),
  constraint pk_user primary key (id)
);

create index ix_blog_author_id on blog (author_id);
alter table blog add constraint fk_blog_author_id foreign key (author_id) references user (id) on delete restrict on update restrict;


# --- !Downs

alter table blog drop foreign key fk_blog_author_id;
drop index ix_blog_author_id on blog;

drop table if exists blog;

drop table if exists comment;

drop table if exists user;


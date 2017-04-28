# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table security_permission (
  id                        bigint not null,
  updated_by                bigint,
  created_by                bigint,
  created                   timestamp,
  updated                   timestamp,
  value                     varchar(255) not null,
  section                   varchar(255) not null,
  icon                      varchar(255),
  constraint pk_security_permission primary key (id))
;

create table security_role (
  id                        bigint not null,
  updated_by                bigint,
  created_by                bigint,
  created                   timestamp,
  updated                   timestamp,
  name                      varchar(255),
  constraint pk_security_role primary key (id))
;

create table todo (
  id                        bigint not null,
  updated_by                bigint,
  created_by                bigint,
  created                   timestamp,
  updated                   timestamp,
  value                     varchar(140) not null,
  user_id                   bigint,
  done                      boolean,
  constraint pk_todo primary key (id))
;

create table user (
  id                        bigint not null,
  updated_by                bigint,
  created_by                bigint,
  created                   timestamp,
  updated                   timestamp,
  auth_token                varchar(255),
  email                     varchar(256) not null,
  sha_password              varbinary(64) not null,
  name                      varchar(256) not null,
  last_name                 varchar(256) not null,
  role_id                   bigint,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;


create table security_role_security_permissio (
  security_role_id               bigint not null,
  security_permission_id         bigint not null,
  constraint pk_security_role_security_permissio primary key (security_role_id, security_permission_id))
;
create sequence security_permission_seq;

create sequence security_role_seq;

create sequence todo_seq;

create sequence user_seq;

alter table security_permission add constraint fk_security_permission_updated_1 foreign key (updated_by) references user (id) on delete restrict on update restrict;
create index ix_security_permission_updated_1 on security_permission (updated_by);
alter table security_permission add constraint fk_security_permission_created_2 foreign key (created_by) references user (id) on delete restrict on update restrict;
create index ix_security_permission_created_2 on security_permission (created_by);
alter table security_role add constraint fk_security_role_updatedBy_3 foreign key (updated_by) references user (id) on delete restrict on update restrict;
create index ix_security_role_updatedBy_3 on security_role (updated_by);
alter table security_role add constraint fk_security_role_createdBy_4 foreign key (created_by) references user (id) on delete restrict on update restrict;
create index ix_security_role_createdBy_4 on security_role (created_by);
alter table todo add constraint fk_todo_updatedBy_5 foreign key (updated_by) references user (id) on delete restrict on update restrict;
create index ix_todo_updatedBy_5 on todo (updated_by);
alter table todo add constraint fk_todo_createdBy_6 foreign key (created_by) references user (id) on delete restrict on update restrict;
create index ix_todo_createdBy_6 on todo (created_by);
alter table todo add constraint fk_todo_user_7 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_todo_user_7 on todo (user_id);
alter table user add constraint fk_user_updatedBy_8 foreign key (updated_by) references user (id) on delete restrict on update restrict;
create index ix_user_updatedBy_8 on user (updated_by);
alter table user add constraint fk_user_createdBy_9 foreign key (created_by) references user (id) on delete restrict on update restrict;
create index ix_user_createdBy_9 on user (created_by);
alter table user add constraint fk_user_role_10 foreign key (role_id) references security_role (id) on delete restrict on update restrict;
create index ix_user_role_10 on user (role_id);



alter table security_role_security_permissio add constraint fk_security_role_security_per_01 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table security_role_security_permissio add constraint fk_security_role_security_per_02 foreign key (security_permission_id) references security_permission (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists security_permission;

drop table if exists security_role;

drop table if exists security_role_security_permissio;

drop table if exists todo;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists security_permission_seq;

drop sequence if exists security_role_seq;

drop sequence if exists todo_seq;

drop sequence if exists user_seq;


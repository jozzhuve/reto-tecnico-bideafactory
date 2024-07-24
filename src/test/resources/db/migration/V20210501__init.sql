CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists user_actions
(
    _id            uuid default uuid_generate_v4() not null
    constraint user_actions_pk
    primary key,
    action_type    varchar(100),
    item_type      varchar(100),
    user_token     text,
    metadata       text,
    module_name    varchar(100),
    create_at      bigint  default date_part('epoch'::text, now()),
    event_type_id  varchar(100),
    is_read        bool default false
    );

comment on table user_actions is 'Registra las acciones de los usuarios';


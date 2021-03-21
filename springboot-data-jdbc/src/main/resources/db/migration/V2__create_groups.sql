CREATE TABLE groups (
    id          UUID not null default random_uuid() primary key,
    --
    name        varchar(255) not null unique
);

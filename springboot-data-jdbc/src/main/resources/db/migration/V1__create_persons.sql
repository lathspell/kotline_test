CREATE TABLE persons (
    id          UUID not null primary key,
    --
    gid         int not null,
    name        varchar(255) not null unique,
    --
    created_at  timestamp not null,
    updated_at  timestamp not null
);

CREATE INDEX ON persons (gid);
CREATE INDEX ON persons (created_at);
CREATE INDEX ON persons (updated_at);

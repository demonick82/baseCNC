CREATE TABLE IF NOT EXISTS authorities
(
    id        serial primary key,
    authority varchar not null unique
);
CREATE TABLE IF NOT EXISTS drawings
(
    id   bigserial primary key,
    path varchar
);
CREATE TABLE IF NOT EXISTS technological_processes
(
    id         bigserial primary key,
    path       varchar,
    details_id bigint references details (id)
);

CREATE TABLE IF NOT EXISTS details
(
    id             bigserial primary key,
    drawing_number varchar unique,
    name           varchar,
    drawings_id    bigint references drawings (id)

);

CREATE TABLE IF NOT EXISTS programs
(
    id                  bigserial primary key,
    program_name        varchar,
    program_path        varchar,
    ug_version          varchar,
    model_path          varchar,
    created             date,
    updated             date,
    details_id          bigint references details (id),
    programmers_id      bigint references users (id),
    machines_id         bigint references machines (id),
    operation_blanks_id bigint references operation_blanks (id)
);

CREATE TABLE IF NOT EXISTS machines
(
    id           bigserial primary key,
    machine_name varchar
);

CREATE TABLE IF NOT EXISTS users
(
    id           bigserial primary key,
    name         varchar,
    login        varchar,
    password     varchar,
    enabled      boolean default true,
    authority_id int not null references authorities (id)
);

CREATE TABLE IF NOT EXISTS operation_blanks
(
    id      bigserial primary key,
    name    varchar,
    path    varchar,
    comment varchar
);

CREATE TABLE IF NOT EXISTS operations
(
    id             bigserial primary key,
    program_number varchar,
    operation_name varchar,
    operation_type varchar,
    spindle_speed  float8,
    feed_rate      float8,
    stock_part     float8,
    stock_floor    float8,
    cut_depth      float8,
    machine_time   float8,
    description    varchar,
    tools_id       bigint,
    operations_id  bigint references programs (id)

);

CREATE TABLE IF NOT EXISTS bore_tools
(
    id            bigserial primary key,
    tool_number   int,
    tool_name     varchar,
    tool_diametr  float8,
    length        float8,
    flute_length  float8,
    flute_number  int,
    corner_radius float8
);

CREATE TABLE IF NOT EXISTS champher_mill_tools
(
    id                bigserial primary key,
    tool_number       int,
    tool_name         varchar,
    tool_diametr      float8,
    length            float8,
    flute_length      float8,
    flute_number      int,
    tool_tapper_angle float8,
    chamfer_length    float8
);

CREATE TABLE IF NOT EXISTS drilling_tools
(
    id               bigserial primary key,
    tool_number      int,
    tool_name        varchar,
    tool_diametr     float8,
    length           float8,
    flute_length     float8,
    flute_number     int,
    tool_point_angle float8
);

CREATE TABLE IF NOT EXISTS milling_tools
(
    id            bigserial primary key,
    tool_number   int,
    tool_name     varchar,
    tool_diametr  float8,
    length        float8,
    flute_length  float8,
    flute_number  int,
    corner_radius float8
);

CREATE TABLE IF NOT EXISTS spot_drilling_tools
(
    id               bigserial primary key,
    tool_number      int,
    tool_name        varchar,
    tool_diametr     float8,
    length           float8,
    flute_length     float8,
    flute_number     int,
    tool_point_angle float8
);

CREATE TABLE IF NOT EXISTS tap_tools
(
    id           bigserial primary key,
    tool_number  int,
    tool_name    varchar,
    tool_diametr float8,
    length       float8,
    flute_length float8,
    flute_number int,
    tool_pitch   float8
);

CREATE TABLE IF NOT EXISTS t_cutter_mill_tools
(
    id                  bigserial primary key,
    tool_number         int,
    tool_name           varchar,
    tool_diametr        float8,
    length              float8,
    flute_length        float8,
    flute_number        int,
    shank_diameter      float8,
    lower_corner_radius float8,
    upper_corner_radius float8
);

CREATE TABLE IF NOT EXISTS thread_mill_tools
(
    id           bigserial primary key,
    tool_number  int,
    tool_name    varchar,
    tool_diametr float8,
    length       float8,
    flute_length float8,
    flute_number int,
    tool_pitch   float8
);

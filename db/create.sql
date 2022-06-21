CREATE TABLE IF NOT EXISTS authorities
(
    id        serial primary key,
    authority varchar not null unique
);

CREATE TABLE IF NOT EXISTS details
(
    id             bigserial primary key,
    drawing_number varchar,
    name           varchar
);

CREATE TABLE IF NOT EXISTS programs
(
    id             bigserial primary key,
    program_name   varchar,
    program_path   varchar,
    ug_version     varchar,
    model_path     varchar,
    created        date,
    updated        date,
    details_id     bigint references programs (id),
    programmers_id bigint references programs (id),
    machines_id    bigint references programs (id)
);

CREATE TABLE IF NOT EXISTS machines
(
    id           bigserial primary key,
    machine_name varchar
);

CREATE TABLE IF NOT EXISTS programmers
(
    id   bigserial primary key,
    name varchar
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
    tools_id       bigint references operations (id)
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
    id              bigserial primary key,
    toolNumber      int,
    toolName        varchar,
    toolDiametr     float8,
    length          float8,
    fluteLength     float8,
    fluteNumber     int,
    toolTapperAngle float8,
    chamferLength   float8
);

CREATE TABLE IF NOT EXISTS drilling_tools
(
    id             bigserial primary key,
    toolNumber     int,
    toolName       varchar,
    toolDiametr    float8,
    length         float8,
    fluteLength    float8,
    fluteNumber    int,
    toolPointAngle float8
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

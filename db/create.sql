CREATE TABLE IF NOT EXISTS authorities
(
    id        serial primary key,
    authority varchar not null unique
);

CREATE TABLE IF NOT EXISTS details
(
    id            bigserial primary key,
    drawingNumber varchar,
    name          varchar
);

CREATE TABLE IF NOT EXISTS programs
(
    id          bigserial primary key,
    programName varchar,
    programPath varchar,
    ugVersion   varchar,
    modelPath   varchar,
    created     date,
    updated     date,
    details_id bigint references details (id)
);

CREATE TABLE IF NOT EXISTS machines
(
    id          bigserial primary key,
    machineName varchar,
    program_id bigint references programs (id)
);

CREATE TABLE IF NOT EXISTS programmers
(
    id             bigserial primary key,
    name           varchar,
    program_id bigint references programs (id)
);

CREATE TABLE IF NOT EXISTS operation_blanks
(
    id             bigserial primary key,
    name           varchar,
    path           varchar,
    comment        varchar,
    program_id bigint references programs (id)
);

CREATE TABLE IF NOT EXISTS operations
(
    id            bigserial primary key,
    programNumber varchar,
    path          varchar,
    operationName varchar,
    operationType varchar,
    spindleSpeed  float8,
    feedRate      float8,
    stockPart     float8,
    stockFloor    float8,
    cutDepth      float8,
    machineTime   float8,
    description   varchar,
    program_id bigint references programs (id)
);

CREATE TABLE IF NOT EXISTS bore_tools
(
    id            bigserial primary key,
    toolNumber    int,
    toolName      varchar,
    toolDiametr   float8,
    length        float8,
    fluteLength   float8,
    fluteNumber   int,
    cornerRadius  float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS champher_mill_tools
(
    id                     bigserial primary key,
    toolNumber             int,
    toolName               varchar,
    toolDiametr            float8,
    length                 float8,
    fluteLength            float8,
    fluteNumber            int,
    toolTapperAngle        float8,
    chamferLength          float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS drilling_tools
(
    id                bigserial primary key,
    toolNumber        int,
    toolName          varchar,
    toolDiametr       float8,
    length            float8,
    fluteLength       float8,
    fluteNumber       int,
    toolPointAngle    float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS milling_tools
(
    id                bigserial primary key,
    toolNumber        int,
    toolName          varchar,
    toolDiametr       float8,
    length            float8,
    fluteLength       float8,
    fluteNumber       int,
    cornerRadius      float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS spot_drilling_tools
(
    id                     bigserial primary key,
    toolNumber             int,
    toolName               varchar,
    toolDiametr            float8,
    length                 float8,
    fluteLength            float8,
    fluteNumber            int,
    toolPointAngle         float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS tap_tools
(
    id           bigserial primary key,
    toolNumber   int,
    toolName     varchar,
    toolDiametr  float8,
    length       float8,
    fluteLength  float8,
    fluteNumber  int,
    toolPitch    float8,
    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS t_cutter_mill_tools
(
    id                     bigserial primary key,
    toolNumber             int,
    toolName               varchar,
    toolDiametr            float8,
    length                 float8,
    fluteLength            float8,
    fluteNumber            int,
    shankDiameter          float8,
    lowerCornerRadius      float8,
    upperCornerRadius      float8,

    operation_id bigint references operations (id)
);

CREATE TABLE IF NOT EXISTS thread_mill_tools
(
    id                   bigserial primary key,
    toolNumber           int,
    toolName             varchar,
    toolDiametr          float8,
    length               float8,
    fluteLength          float8,
    fluteNumber          int,
    toolPitch            float8,
    operation_id bigint references operations (id)
);

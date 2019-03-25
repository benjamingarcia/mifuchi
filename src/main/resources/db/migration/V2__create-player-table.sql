CREATE TABLE player
(
  uuid uuid not null,
  name varchar not null,
  color varchar not null,
  deck Json not null,
  discard Json not null,
  hand Json not null,
  treasure Json not null
)
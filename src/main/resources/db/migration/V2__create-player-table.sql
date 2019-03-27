CREATE TABLE player
(
  uuid uuid not null,
  name varchar not null,
  color varchar not null
);

CREATE TABLE treasure
(
  uuid uuid not null,
  name varchar not null,
  state varchar not null,
  player_id uuid,
  game_id uuid not null
);

CREATE TABLE card
(
  uuid uuid not null,
  name varchar not null,
  state varchar not null,
  player_id uuid
)
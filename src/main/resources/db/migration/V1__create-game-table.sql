CREATE TABLE game
(
  uuid uuid not null,
  gamer1_id uuid not null,
  gamer2_id uuid not null,
  wawabbit_position int not null,
  wawabbit_orientation varchar not null
)
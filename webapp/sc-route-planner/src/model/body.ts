class Planet extends Entity {
  radius: number;
  omRadius: number;
  type: 'STAR'|'PLANET'|'MOON';

  parent?: Planet;
  system?: System;
}

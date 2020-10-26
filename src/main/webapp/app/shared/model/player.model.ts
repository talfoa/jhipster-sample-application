export interface IPlayer {
  id?: number;
  name?: string;
  nickName?: string;
  shirtNumber?: number;
  position?: string;
  teamId?: number;
}

export class Player implements IPlayer {
  constructor(
    public id?: number,
    public name?: string,
    public nickName?: string,
    public shirtNumber?: number,
    public position?: string,
    public teamId?: number
  ) {}
}

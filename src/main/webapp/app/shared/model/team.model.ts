import { ILeague } from 'app/shared/model/league.model';

export interface ITeam {
  id?: number;
  name?: string;
  color?: string;
  flag?: string;
  sportId?: number;
  leagues?: ILeague[];
}

export class Team implements ITeam {
  constructor(
    public id?: number,
    public name?: string,
    public color?: string,
    public flag?: string,
    public sportId?: number,
    public leagues?: ILeague[]
  ) {}
}

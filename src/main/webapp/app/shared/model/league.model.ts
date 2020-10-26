import { ITeam } from 'app/shared/model/team.model';

export interface ILeague {
  id?: number;
  name?: string;
  sportId?: number;
  regionId?: number;
  teams?: ITeam[];
}

export class League implements ILeague {
  constructor(public id?: number, public name?: string, public sportId?: number, public regionId?: number, public teams?: ITeam[]) {}
}

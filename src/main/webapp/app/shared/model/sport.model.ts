import { ILeague } from 'app/shared/model/league.model';
import { ITeam } from 'app/shared/model/team.model';

export interface ISport {
  id?: number;
  name?: string;
  leagues?: ILeague[];
  sports?: ITeam[];
}

export class Sport implements ISport {
  constructor(public id?: number, public name?: string, public leagues?: ILeague[], public sports?: ITeam[]) {}
}

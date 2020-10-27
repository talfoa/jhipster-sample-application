import { ILeague } from 'app/shared/model/league.model';

export interface IRegion {
  id?: number;
  regionName?: string;
  leagues?: ILeague[];
}

export class Region implements IRegion {
  constructor(public id?: number, public regionName?: string, public leagues?: ILeague[]) {}
}

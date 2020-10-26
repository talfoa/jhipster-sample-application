import { Moment } from 'moment';

export interface IMatch {
  id?: number;
  timeOfMessage?: Moment;
  matchId?: number;
  matchDate?: Moment;
  cornerSending?: boolean;
  sportId?: number;
  regionId?: number;
  leagueId?: number;
  homeTeamId?: number;
  awayTeamId?: number;
}

export class Match implements IMatch {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public matchId?: number,
    public matchDate?: Moment,
    public cornerSending?: boolean,
    public sportId?: number,
    public regionId?: number,
    public leagueId?: number,
    public homeTeamId?: number,
    public awayTeamId?: number
  ) {
    this.cornerSending = this.cornerSending || false;
  }
}

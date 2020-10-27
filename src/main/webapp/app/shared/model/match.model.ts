import { Moment } from 'moment';
import { IGameEvent } from 'app/shared/model/game-event.model';
import { IScoreBoard } from 'app/shared/model/score-board.model';
import { IOpportunity } from 'app/shared/model/opportunity.model';

export interface IMatch {
  id?: number;
  timeOfMessage?: Moment;
  matchId?: number;
  matchDate?: Moment;
  cornerSending?: boolean;
  gameEvents?: IGameEvent[];
  scoreBoards?: IScoreBoard[];
  opportunities?: IOpportunity[];
  homeTeamId?: number;
  awayTeamId?: number;
  leagueId?: number;
  sportId?: number;
  regionId?: number;
}

export class Match implements IMatch {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public matchId?: number,
    public matchDate?: Moment,
    public cornerSending?: boolean,
    public gameEvents?: IGameEvent[],
    public scoreBoards?: IScoreBoard[],
    public opportunities?: IOpportunity[],
    public homeTeamId?: number,
    public awayTeamId?: number,
    public leagueId?: number,
    public sportId?: number,
    public regionId?: number
  ) {
    this.cornerSending = this.cornerSending || false;
  }
}

import { Moment } from 'moment';
import { GameEventType } from 'app/shared/model/enumerations/game-event-type.model';
import { TeamHomeOrAway } from 'app/shared/model/enumerations/team-home-or-away.model';

export interface IGameEvent {
  id?: number;
  timeOfMessage?: Moment;
  gameEventId?: number;
  eventType?: GameEventType;
  team?: TeamHomeOrAway;
  active?: boolean;
  timeOfEventOccurence?: Moment;
  matchId?: number;
}

export class GameEvent implements IGameEvent {
  constructor(
    public id?: number,
    public timeOfMessage?: Moment,
    public gameEventId?: number,
    public eventType?: GameEventType,
    public team?: TeamHomeOrAway,
    public active?: boolean,
    public timeOfEventOccurence?: Moment,
    public matchId?: number
  ) {
    this.active = this.active || false;
  }
}

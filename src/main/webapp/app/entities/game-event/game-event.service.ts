import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGameEvent } from 'app/shared/model/game-event.model';

type EntityResponseType = HttpResponse<IGameEvent>;
type EntityArrayResponseType = HttpResponse<IGameEvent[]>;

@Injectable({ providedIn: 'root' })
export class GameEventService {
  public resourceUrl = SERVER_API_URL + 'api/game-events';

  constructor(protected http: HttpClient) {}

  create(gameEvent: IGameEvent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gameEvent);
    return this.http
      .post<IGameEvent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gameEvent: IGameEvent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gameEvent);
    return this.http
      .put<IGameEvent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGameEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGameEvent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gameEvent: IGameEvent): IGameEvent {
    const copy: IGameEvent = Object.assign({}, gameEvent, {
      timeOfMessage: gameEvent.timeOfMessage && gameEvent.timeOfMessage.isValid() ? gameEvent.timeOfMessage.toJSON() : undefined,
      timeOfEventOccurence:
        gameEvent.timeOfEventOccurence && gameEvent.timeOfEventOccurence.isValid() ? gameEvent.timeOfEventOccurence.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.timeOfMessage = res.body.timeOfMessage ? moment(res.body.timeOfMessage) : undefined;
      res.body.timeOfEventOccurence = res.body.timeOfEventOccurence ? moment(res.body.timeOfEventOccurence) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gameEvent: IGameEvent) => {
        gameEvent.timeOfMessage = gameEvent.timeOfMessage ? moment(gameEvent.timeOfMessage) : undefined;
        gameEvent.timeOfEventOccurence = gameEvent.timeOfEventOccurence ? moment(gameEvent.timeOfEventOccurence) : undefined;
      });
    }
    return res;
  }
}

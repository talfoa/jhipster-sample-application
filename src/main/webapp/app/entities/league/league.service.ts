import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILeague } from 'app/shared/model/league.model';

type EntityResponseType = HttpResponse<ILeague>;
type EntityArrayResponseType = HttpResponse<ILeague[]>;

@Injectable({ providedIn: 'root' })
export class LeagueService {
  public resourceUrl = SERVER_API_URL + 'api/leagues';

  constructor(protected http: HttpClient) {}

  create(league: ILeague): Observable<EntityResponseType> {
    return this.http.post<ILeague>(this.resourceUrl, league, { observe: 'response' });
  }

  update(league: ILeague): Observable<EntityResponseType> {
    return this.http.put<ILeague>(this.resourceUrl, league, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILeague>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILeague[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

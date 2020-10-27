import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGameEvent, GameEvent } from 'app/shared/model/game-event.model';
import { GameEventService } from './game-event.service';
import { GameEventComponent } from './game-event.component';
import { GameEventDetailComponent } from './game-event-detail.component';
import { GameEventUpdateComponent } from './game-event-update.component';

@Injectable({ providedIn: 'root' })
export class GameEventResolve implements Resolve<IGameEvent> {
  constructor(private service: GameEventService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGameEvent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gameEvent: HttpResponse<GameEvent>) => {
          if (gameEvent.body) {
            return of(gameEvent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GameEvent());
  }
}

export const gameEventRoute: Routes = [
  {
    path: '',
    component: GameEventComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.gameEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GameEventDetailComponent,
    resolve: {
      gameEvent: GameEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.gameEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GameEventUpdateComponent,
    resolve: {
      gameEvent: GameEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.gameEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GameEventUpdateComponent,
    resolve: {
      gameEvent: GameEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.gameEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

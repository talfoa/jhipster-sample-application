import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.JhipsterSampleApplicationRegionModule),
      },
      {
        path: 'sport',
        loadChildren: () => import('./sport/sport.module').then(m => m.JhipsterSampleApplicationSportModule),
      },
      {
        path: 'league',
        loadChildren: () => import('./league/league.module').then(m => m.JhipsterSampleApplicationLeagueModule),
      },
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.JhipsterSampleApplicationTeamModule),
      },
      {
        path: 'player',
        loadChildren: () => import('./player/player.module').then(m => m.JhipsterSampleApplicationPlayerModule),
      },
      {
        path: 'match',
        loadChildren: () => import('./match/match.module').then(m => m.JhipsterSampleApplicationMatchModule),
      },
      {
        path: 'game-event',
        loadChildren: () => import('./game-event/game-event.module').then(m => m.JhipsterSampleApplicationGameEventModule),
      },
      {
        path: 'score-board',
        loadChildren: () => import('./score-board/score-board.module').then(m => m.JhipsterSampleApplicationScoreBoardModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationEntityModule {}

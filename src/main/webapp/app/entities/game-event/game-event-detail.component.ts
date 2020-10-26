import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGameEvent } from 'app/shared/model/game-event.model';

@Component({
  selector: 'jhi-game-event-detail',
  templateUrl: './game-event-detail.component.html',
})
export class GameEventDetailComponent implements OnInit {
  gameEvent: IGameEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gameEvent }) => (this.gameEvent = gameEvent));
  }

  previousState(): void {
    window.history.back();
  }
}

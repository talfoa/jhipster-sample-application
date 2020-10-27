import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGameEvent, GameEvent } from 'app/shared/model/game-event.model';
import { GameEventService } from './game-event.service';
import { IMatch } from 'app/shared/model/match.model';
import { MatchService } from 'app/entities/match/match.service';

@Component({
  selector: 'jhi-game-event-update',
  templateUrl: './game-event-update.component.html',
})
export class GameEventUpdateComponent implements OnInit {
  isSaving = false;
  matches: IMatch[] = [];

  editForm = this.fb.group({
    id: [],
    timeOfMessage: [],
    gameEventId: [],
    eventType: [],
    team: [],
    active: [],
    timeOfEventOccurence: [],
    matchId: [],
  });

  constructor(
    protected gameEventService: GameEventService,
    protected matchService: MatchService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gameEvent }) => {
      if (!gameEvent.id) {
        const today = moment().startOf('day');
        gameEvent.timeOfMessage = today;
        gameEvent.timeOfEventOccurence = today;
      }

      this.updateForm(gameEvent);

      this.matchService.query().subscribe((res: HttpResponse<IMatch[]>) => (this.matches = res.body || []));
    });
  }

  updateForm(gameEvent: IGameEvent): void {
    this.editForm.patchValue({
      id: gameEvent.id,
      timeOfMessage: gameEvent.timeOfMessage ? gameEvent.timeOfMessage.format(DATE_TIME_FORMAT) : null,
      gameEventId: gameEvent.gameEventId,
      eventType: gameEvent.eventType,
      team: gameEvent.team,
      active: gameEvent.active,
      timeOfEventOccurence: gameEvent.timeOfEventOccurence ? gameEvent.timeOfEventOccurence.format(DATE_TIME_FORMAT) : null,
      matchId: gameEvent.matchId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gameEvent = this.createFromForm();
    if (gameEvent.id !== undefined) {
      this.subscribeToSaveResponse(this.gameEventService.update(gameEvent));
    } else {
      this.subscribeToSaveResponse(this.gameEventService.create(gameEvent));
    }
  }

  private createFromForm(): IGameEvent {
    return {
      ...new GameEvent(),
      id: this.editForm.get(['id'])!.value,
      timeOfMessage: this.editForm.get(['timeOfMessage'])!.value
        ? moment(this.editForm.get(['timeOfMessage'])!.value, DATE_TIME_FORMAT)
        : undefined,
      gameEventId: this.editForm.get(['gameEventId'])!.value,
      eventType: this.editForm.get(['eventType'])!.value,
      team: this.editForm.get(['team'])!.value,
      active: this.editForm.get(['active'])!.value,
      timeOfEventOccurence: this.editForm.get(['timeOfEventOccurence'])!.value
        ? moment(this.editForm.get(['timeOfEventOccurence'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matchId: this.editForm.get(['matchId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGameEvent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IMatch): any {
    return item.id;
  }
}

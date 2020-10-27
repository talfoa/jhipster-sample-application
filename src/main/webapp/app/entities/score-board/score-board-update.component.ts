import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IScoreBoard, ScoreBoard } from 'app/shared/model/score-board.model';
import { ScoreBoardService } from './score-board.service';
import { IMatch } from 'app/shared/model/match.model';
import { MatchService } from 'app/entities/match/match.service';

@Component({
  selector: 'jhi-score-board-update',
  templateUrl: './score-board-update.component.html',
})
export class ScoreBoardUpdateComponent implements OnInit {
  isSaving = false;
  matches: IMatch[] = [];

  editForm = this.fb.group({
    id: [],
    timeOfMessage: [],
    gamePart: [],
    score: [],
    scorePart: [],
    hidden: [],
    hideTimer: [],
    remainingTimeInPeriod: [],
    relativePlayerCount: [],
    matchId: [],
  });

  constructor(
    protected scoreBoardService: ScoreBoardService,
    protected matchService: MatchService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scoreBoard }) => {
      if (!scoreBoard.id) {
        const today = moment().startOf('day');
        scoreBoard.timeOfMessage = today;
      }

      this.updateForm(scoreBoard);

      this.matchService.query().subscribe((res: HttpResponse<IMatch[]>) => (this.matches = res.body || []));
    });
  }

  updateForm(scoreBoard: IScoreBoard): void {
    this.editForm.patchValue({
      id: scoreBoard.id,
      timeOfMessage: scoreBoard.timeOfMessage ? scoreBoard.timeOfMessage.format(DATE_TIME_FORMAT) : null,
      gamePart: scoreBoard.gamePart,
      score: scoreBoard.score,
      scorePart: scoreBoard.scorePart,
      hidden: scoreBoard.hidden,
      hideTimer: scoreBoard.hideTimer,
      remainingTimeInPeriod: scoreBoard.remainingTimeInPeriod,
      relativePlayerCount: scoreBoard.relativePlayerCount,
      matchId: scoreBoard.matchId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const scoreBoard = this.createFromForm();
    if (scoreBoard.id !== undefined) {
      this.subscribeToSaveResponse(this.scoreBoardService.update(scoreBoard));
    } else {
      this.subscribeToSaveResponse(this.scoreBoardService.create(scoreBoard));
    }
  }

  private createFromForm(): IScoreBoard {
    return {
      ...new ScoreBoard(),
      id: this.editForm.get(['id'])!.value,
      timeOfMessage: this.editForm.get(['timeOfMessage'])!.value
        ? moment(this.editForm.get(['timeOfMessage'])!.value, DATE_TIME_FORMAT)
        : undefined,
      gamePart: this.editForm.get(['gamePart'])!.value,
      score: this.editForm.get(['score'])!.value,
      scorePart: this.editForm.get(['scorePart'])!.value,
      hidden: this.editForm.get(['hidden'])!.value,
      hideTimer: this.editForm.get(['hideTimer'])!.value,
      remainingTimeInPeriod: this.editForm.get(['remainingTimeInPeriod'])!.value,
      relativePlayerCount: this.editForm.get(['relativePlayerCount'])!.value,
      matchId: this.editForm.get(['matchId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScoreBoard>>): void {
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

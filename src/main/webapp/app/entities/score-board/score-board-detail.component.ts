import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScoreBoard } from 'app/shared/model/score-board.model';

@Component({
  selector: 'jhi-score-board-detail',
  templateUrl: './score-board-detail.component.html',
})
export class ScoreBoardDetailComponent implements OnInit {
  scoreBoard: IScoreBoard | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scoreBoard }) => (this.scoreBoard = scoreBoard));
  }

  previousState(): void {
    window.history.back();
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScoreBoard } from 'app/shared/model/score-board.model';
import { ScoreBoardService } from './score-board.service';

@Component({
  templateUrl: './score-board-delete-dialog.component.html',
})
export class ScoreBoardDeleteDialogComponent {
  scoreBoard?: IScoreBoard;

  constructor(
    protected scoreBoardService: ScoreBoardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.scoreBoardService.delete(id).subscribe(() => {
      this.eventManager.broadcast('scoreBoardListModification');
      this.activeModal.close();
    });
  }
}

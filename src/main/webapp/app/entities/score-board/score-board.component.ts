import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IScoreBoard } from 'app/shared/model/score-board.model';
import { ScoreBoardService } from './score-board.service';
import { ScoreBoardDeleteDialogComponent } from './score-board-delete-dialog.component';

@Component({
  selector: 'jhi-score-board',
  templateUrl: './score-board.component.html',
})
export class ScoreBoardComponent implements OnInit, OnDestroy {
  scoreBoards?: IScoreBoard[];
  eventSubscriber?: Subscription;

  constructor(protected scoreBoardService: ScoreBoardService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.scoreBoardService.query().subscribe((res: HttpResponse<IScoreBoard[]>) => (this.scoreBoards = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInScoreBoards();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IScoreBoard): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInScoreBoards(): void {
    this.eventSubscriber = this.eventManager.subscribe('scoreBoardListModification', () => this.loadAll());
  }

  delete(scoreBoard: IScoreBoard): void {
    const modalRef = this.modalService.open(ScoreBoardDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.scoreBoard = scoreBoard;
  }
}

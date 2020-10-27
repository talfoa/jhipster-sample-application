import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGameEvent } from 'app/shared/model/game-event.model';
import { GameEventService } from './game-event.service';
import { GameEventDeleteDialogComponent } from './game-event-delete-dialog.component';

@Component({
  selector: 'jhi-game-event',
  templateUrl: './game-event.component.html',
})
export class GameEventComponent implements OnInit, OnDestroy {
  gameEvents?: IGameEvent[];
  eventSubscriber?: Subscription;

  constructor(protected gameEventService: GameEventService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.gameEventService.query().subscribe((res: HttpResponse<IGameEvent[]>) => (this.gameEvents = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGameEvents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGameEvent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGameEvents(): void {
    this.eventSubscriber = this.eventManager.subscribe('gameEventListModification', () => this.loadAll());
  }

  delete(gameEvent: IGameEvent): void {
    const modalRef = this.modalService.open(GameEventDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gameEvent = gameEvent;
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGameEvent } from 'app/shared/model/game-event.model';
import { GameEventService } from './game-event.service';

@Component({
  templateUrl: './game-event-delete-dialog.component.html',
})
export class GameEventDeleteDialogComponent {
  gameEvent?: IGameEvent;

  constructor(protected gameEventService: GameEventService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gameEventService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gameEventListModification');
      this.activeModal.close();
    });
  }
}

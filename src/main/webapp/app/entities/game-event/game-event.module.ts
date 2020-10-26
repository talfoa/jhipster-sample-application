import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { GameEventComponent } from './game-event.component';
import { GameEventDetailComponent } from './game-event-detail.component';
import { GameEventUpdateComponent } from './game-event-update.component';
import { GameEventDeleteDialogComponent } from './game-event-delete-dialog.component';
import { gameEventRoute } from './game-event.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(gameEventRoute)],
  declarations: [GameEventComponent, GameEventDetailComponent, GameEventUpdateComponent, GameEventDeleteDialogComponent],
  entryComponents: [GameEventDeleteDialogComponent],
})
export class JhipsterSampleApplicationGameEventModule {}

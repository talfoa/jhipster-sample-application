import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { ScoreBoardComponent } from './score-board.component';
import { ScoreBoardDetailComponent } from './score-board-detail.component';
import { ScoreBoardUpdateComponent } from './score-board-update.component';
import { ScoreBoardDeleteDialogComponent } from './score-board-delete-dialog.component';
import { scoreBoardRoute } from './score-board.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(scoreBoardRoute)],
  declarations: [ScoreBoardComponent, ScoreBoardDetailComponent, ScoreBoardUpdateComponent, ScoreBoardDeleteDialogComponent],
  entryComponents: [ScoreBoardDeleteDialogComponent],
})
export class JhipsterSampleApplicationScoreBoardModule {}

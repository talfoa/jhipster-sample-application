import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { OpportunityComponent } from './opportunity.component';
import { OpportunityDetailComponent } from './opportunity-detail.component';
import { OpportunityUpdateComponent } from './opportunity-update.component';
import { OpportunityDeleteDialogComponent } from './opportunity-delete-dialog.component';
import { opportunityRoute } from './opportunity.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(opportunityRoute)],
  declarations: [OpportunityComponent, OpportunityDetailComponent, OpportunityUpdateComponent, OpportunityDeleteDialogComponent],
  entryComponents: [OpportunityDeleteDialogComponent],
})
export class JhipsterSampleApplicationOpportunityModule {}

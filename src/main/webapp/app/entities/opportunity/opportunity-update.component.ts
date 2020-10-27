import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOpportunity, Opportunity } from 'app/shared/model/opportunity.model';
import { OpportunityService } from './opportunity.service';
import { IMatch } from 'app/shared/model/match.model';
import { MatchService } from 'app/entities/match/match.service';

@Component({
  selector: 'jhi-opportunity-update',
  templateUrl: './opportunity-update.component.html',
})
export class OpportunityUpdateComponent implements OnInit {
  isSaving = false;
  matches: IMatch[] = [];

  editForm = this.fb.group({
    id: [],
    timeOfMessage: [],
    opportunityId: [],
    type: [],
    handicap: [],
    line: [],
    sequence: [],
    tradingStatus: [],
    actualTradingTime: [],
    note: [],
    betStop: [],
    results: [],
    matchId: [],
  });

  constructor(
    protected opportunityService: OpportunityService,
    protected matchService: MatchService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ opportunity }) => {
      if (!opportunity.id) {
        const today = moment().startOf('day');
        opportunity.timeOfMessage = today;
        opportunity.actualTradingTime = today;
      }

      this.updateForm(opportunity);

      this.matchService.query().subscribe((res: HttpResponse<IMatch[]>) => (this.matches = res.body || []));
    });
  }

  updateForm(opportunity: IOpportunity): void {
    this.editForm.patchValue({
      id: opportunity.id,
      timeOfMessage: opportunity.timeOfMessage ? opportunity.timeOfMessage.format(DATE_TIME_FORMAT) : null,
      opportunityId: opportunity.opportunityId,
      type: opportunity.type,
      handicap: opportunity.handicap,
      line: opportunity.line,
      sequence: opportunity.sequence,
      tradingStatus: opportunity.tradingStatus,
      actualTradingTime: opportunity.actualTradingTime ? opportunity.actualTradingTime.format(DATE_TIME_FORMAT) : null,
      note: opportunity.note,
      betStop: opportunity.betStop,
      results: opportunity.results,
      matchId: opportunity.matchId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const opportunity = this.createFromForm();
    if (opportunity.id !== undefined) {
      this.subscribeToSaveResponse(this.opportunityService.update(opportunity));
    } else {
      this.subscribeToSaveResponse(this.opportunityService.create(opportunity));
    }
  }

  private createFromForm(): IOpportunity {
    return {
      ...new Opportunity(),
      id: this.editForm.get(['id'])!.value,
      timeOfMessage: this.editForm.get(['timeOfMessage'])!.value
        ? moment(this.editForm.get(['timeOfMessage'])!.value, DATE_TIME_FORMAT)
        : undefined,
      opportunityId: this.editForm.get(['opportunityId'])!.value,
      type: this.editForm.get(['type'])!.value,
      handicap: this.editForm.get(['handicap'])!.value,
      line: this.editForm.get(['line'])!.value,
      sequence: this.editForm.get(['sequence'])!.value,
      tradingStatus: this.editForm.get(['tradingStatus'])!.value,
      actualTradingTime: this.editForm.get(['actualTradingTime'])!.value
        ? moment(this.editForm.get(['actualTradingTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      note: this.editForm.get(['note'])!.value,
      betStop: this.editForm.get(['betStop'])!.value,
      results: this.editForm.get(['results'])!.value,
      matchId: this.editForm.get(['matchId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOpportunity>>): void {
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

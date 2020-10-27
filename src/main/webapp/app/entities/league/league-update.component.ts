import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILeague, League } from 'app/shared/model/league.model';
import { LeagueService } from './league.service';
import { ISport } from 'app/shared/model/sport.model';
import { SportService } from 'app/entities/sport/sport.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region/region.service';

type SelectableEntity = ISport | IRegion;

@Component({
  selector: 'jhi-league-update',
  templateUrl: './league-update.component.html',
})
export class LeagueUpdateComponent implements OnInit {
  isSaving = false;
  sports: ISport[] = [];
  regions: IRegion[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    sportId: [],
    regionId: [],
  });

  constructor(
    protected leagueService: LeagueService,
    protected sportService: SportService,
    protected regionService: RegionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ league }) => {
      this.updateForm(league);

      this.sportService.query().subscribe((res: HttpResponse<ISport[]>) => (this.sports = res.body || []));

      this.regionService.query().subscribe((res: HttpResponse<IRegion[]>) => (this.regions = res.body || []));
    });
  }

  updateForm(league: ILeague): void {
    this.editForm.patchValue({
      id: league.id,
      name: league.name,
      sportId: league.sportId,
      regionId: league.regionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const league = this.createFromForm();
    if (league.id !== undefined) {
      this.subscribeToSaveResponse(this.leagueService.update(league));
    } else {
      this.subscribeToSaveResponse(this.leagueService.create(league));
    }
  }

  private createFromForm(): ILeague {
    return {
      ...new League(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sportId: this.editForm.get(['sportId'])!.value,
      regionId: this.editForm.get(['regionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeague>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}

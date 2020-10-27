import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITeam, Team } from 'app/shared/model/team.model';
import { TeamService } from './team.service';
import { ILeague } from 'app/shared/model/league.model';
import { LeagueService } from 'app/entities/league/league.service';
import { ISport } from 'app/shared/model/sport.model';
import { SportService } from 'app/entities/sport/sport.service';

type SelectableEntity = ILeague | ISport;

@Component({
  selector: 'jhi-team-update',
  templateUrl: './team-update.component.html',
})
export class TeamUpdateComponent implements OnInit {
  isSaving = false;
  leagues: ILeague[] = [];
  sports: ISport[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    color: [],
    flag: [],
    leagues: [],
    sportId: [],
  });

  constructor(
    protected teamService: TeamService,
    protected leagueService: LeagueService,
    protected sportService: SportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ team }) => {
      this.updateForm(team);

      this.leagueService.query().subscribe((res: HttpResponse<ILeague[]>) => (this.leagues = res.body || []));

      this.sportService.query().subscribe((res: HttpResponse<ISport[]>) => (this.sports = res.body || []));
    });
  }

  updateForm(team: ITeam): void {
    this.editForm.patchValue({
      id: team.id,
      name: team.name,
      color: team.color,
      flag: team.flag,
      leagues: team.leagues,
      sportId: team.sportId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const team = this.createFromForm();
    if (team.id !== undefined) {
      this.subscribeToSaveResponse(this.teamService.update(team));
    } else {
      this.subscribeToSaveResponse(this.teamService.create(team));
    }
  }

  private createFromForm(): ITeam {
    return {
      ...new Team(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      color: this.editForm.get(['color'])!.value,
      flag: this.editForm.get(['flag'])!.value,
      leagues: this.editForm.get(['leagues'])!.value,
      sportId: this.editForm.get(['sportId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeam>>): void {
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

  getSelected(selectedVals: ILeague[], option: ILeague): ILeague {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMatch, Match } from 'app/shared/model/match.model';
import { MatchService } from './match.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team/team.service';
import { ILeague } from 'app/shared/model/league.model';
import { LeagueService } from 'app/entities/league/league.service';
import { ISport } from 'app/shared/model/sport.model';
import { SportService } from 'app/entities/sport/sport.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region/region.service';

type SelectableEntity = ITeam | ILeague | ISport | IRegion;

@Component({
  selector: 'jhi-match-update',
  templateUrl: './match-update.component.html',
})
export class MatchUpdateComponent implements OnInit {
  isSaving = false;
  teams: ITeam[] = [];
  leagues: ILeague[] = [];
  sports: ISport[] = [];
  regions: IRegion[] = [];

  editForm = this.fb.group({
    id: [],
    timeOfMessage: [],
    matchId: [],
    matchDate: [],
    cornerSending: [],
    homeTeamId: [],
    awayTeamId: [],
    leagueId: [],
    sportId: [],
    regionId: [],
  });

  constructor(
    protected matchService: MatchService,
    protected teamService: TeamService,
    protected leagueService: LeagueService,
    protected sportService: SportService,
    protected regionService: RegionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ match }) => {
      if (!match.id) {
        const today = moment().startOf('day');
        match.timeOfMessage = today;
        match.matchDate = today;
      }

      this.updateForm(match);

      this.teamService.query().subscribe((res: HttpResponse<ITeam[]>) => (this.teams = res.body || []));

      this.leagueService.query().subscribe((res: HttpResponse<ILeague[]>) => (this.leagues = res.body || []));

      this.sportService.query().subscribe((res: HttpResponse<ISport[]>) => (this.sports = res.body || []));

      this.regionService.query().subscribe((res: HttpResponse<IRegion[]>) => (this.regions = res.body || []));
    });
  }

  updateForm(match: IMatch): void {
    this.editForm.patchValue({
      id: match.id,
      timeOfMessage: match.timeOfMessage ? match.timeOfMessage.format(DATE_TIME_FORMAT) : null,
      matchId: match.matchId,
      matchDate: match.matchDate ? match.matchDate.format(DATE_TIME_FORMAT) : null,
      cornerSending: match.cornerSending,
      homeTeamId: match.homeTeamId,
      awayTeamId: match.awayTeamId,
      leagueId: match.leagueId,
      sportId: match.sportId,
      regionId: match.regionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const match = this.createFromForm();
    if (match.id !== undefined) {
      this.subscribeToSaveResponse(this.matchService.update(match));
    } else {
      this.subscribeToSaveResponse(this.matchService.create(match));
    }
  }

  private createFromForm(): IMatch {
    return {
      ...new Match(),
      id: this.editForm.get(['id'])!.value,
      timeOfMessage: this.editForm.get(['timeOfMessage'])!.value
        ? moment(this.editForm.get(['timeOfMessage'])!.value, DATE_TIME_FORMAT)
        : undefined,
      matchId: this.editForm.get(['matchId'])!.value,
      matchDate: this.editForm.get(['matchDate'])!.value ? moment(this.editForm.get(['matchDate'])!.value, DATE_TIME_FORMAT) : undefined,
      cornerSending: this.editForm.get(['cornerSending'])!.value,
      homeTeamId: this.editForm.get(['homeTeamId'])!.value,
      awayTeamId: this.editForm.get(['awayTeamId'])!.value,
      leagueId: this.editForm.get(['leagueId'])!.value,
      sportId: this.editForm.get(['sportId'])!.value,
      regionId: this.editForm.get(['regionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatch>>): void {
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

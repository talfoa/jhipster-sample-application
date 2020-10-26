import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMatch, Match } from 'app/shared/model/match.model';
import { MatchService } from './match.service';
import { ISport } from 'app/shared/model/sport.model';
import { SportService } from 'app/entities/sport/sport.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region/region.service';
import { ILeague } from 'app/shared/model/league.model';
import { LeagueService } from 'app/entities/league/league.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team/team.service';

type SelectableEntity = ISport | IRegion | ILeague | ITeam;

@Component({
  selector: 'jhi-match-update',
  templateUrl: './match-update.component.html',
})
export class MatchUpdateComponent implements OnInit {
  isSaving = false;
  sports: ISport[] = [];
  regions: IRegion[] = [];
  leagues: ILeague[] = [];
  hometeams: ITeam[] = [];
  awayteams: ITeam[] = [];

  editForm = this.fb.group({
    id: [],
    timeOfMessage: [],
    matchId: [],
    matchDate: [],
    cornerSending: [],
    sportId: [],
    regionId: [],
    leagueId: [],
    homeTeamId: [],
    awayTeamId: [],
  });

  constructor(
    protected matchService: MatchService,
    protected sportService: SportService,
    protected regionService: RegionService,
    protected leagueService: LeagueService,
    protected teamService: TeamService,
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

      this.sportService
        .query({ filter: 'match-is-null' })
        .pipe(
          map((res: HttpResponse<ISport[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISport[]) => {
          if (!match.sportId) {
            this.sports = resBody;
          } else {
            this.sportService
              .find(match.sportId)
              .pipe(
                map((subRes: HttpResponse<ISport>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISport[]) => (this.sports = concatRes));
          }
        });

      this.regionService
        .query({ filter: 'match-is-null' })
        .pipe(
          map((res: HttpResponse<IRegion[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRegion[]) => {
          if (!match.regionId) {
            this.regions = resBody;
          } else {
            this.regionService
              .find(match.regionId)
              .pipe(
                map((subRes: HttpResponse<IRegion>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRegion[]) => (this.regions = concatRes));
          }
        });

      this.leagueService
        .query({ filter: 'match-is-null' })
        .pipe(
          map((res: HttpResponse<ILeague[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILeague[]) => {
          if (!match.leagueId) {
            this.leagues = resBody;
          } else {
            this.leagueService
              .find(match.leagueId)
              .pipe(
                map((subRes: HttpResponse<ILeague>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILeague[]) => (this.leagues = concatRes));
          }
        });

      this.teamService
        .query({ filter: 'match-is-null' })
        .pipe(
          map((res: HttpResponse<ITeam[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITeam[]) => {
          if (!match.homeTeamId) {
            this.hometeams = resBody;
          } else {
            this.teamService
              .find(match.homeTeamId)
              .pipe(
                map((subRes: HttpResponse<ITeam>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITeam[]) => (this.hometeams = concatRes));
          }
        });

      this.teamService
        .query({ filter: 'match-is-null' })
        .pipe(
          map((res: HttpResponse<ITeam[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITeam[]) => {
          if (!match.awayTeamId) {
            this.awayteams = resBody;
          } else {
            this.teamService
              .find(match.awayTeamId)
              .pipe(
                map((subRes: HttpResponse<ITeam>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITeam[]) => (this.awayteams = concatRes));
          }
        });
    });
  }

  updateForm(match: IMatch): void {
    this.editForm.patchValue({
      id: match.id,
      timeOfMessage: match.timeOfMessage ? match.timeOfMessage.format(DATE_TIME_FORMAT) : null,
      matchId: match.matchId,
      matchDate: match.matchDate ? match.matchDate.format(DATE_TIME_FORMAT) : null,
      cornerSending: match.cornerSending,
      sportId: match.sportId,
      regionId: match.regionId,
      leagueId: match.leagueId,
      homeTeamId: match.homeTeamId,
      awayTeamId: match.awayTeamId,
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
      sportId: this.editForm.get(['sportId'])!.value,
      regionId: this.editForm.get(['regionId'])!.value,
      leagueId: this.editForm.get(['leagueId'])!.value,
      homeTeamId: this.editForm.get(['homeTeamId'])!.value,
      awayTeamId: this.editForm.get(['awayTeamId'])!.value,
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

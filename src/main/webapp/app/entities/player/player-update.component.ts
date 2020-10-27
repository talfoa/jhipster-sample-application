import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPlayer, Player } from 'app/shared/model/player.model';
import { PlayerService } from './player.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team/team.service';

@Component({
  selector: 'jhi-player-update',
  templateUrl: './player-update.component.html',
})
export class PlayerUpdateComponent implements OnInit {
  isSaving = false;
  teams: ITeam[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    nickName: [],
    shirtNumber: [],
    position: [],
    teamId: [],
  });

  constructor(
    protected playerService: PlayerService,
    protected teamService: TeamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ player }) => {
      this.updateForm(player);

      this.teamService.query().subscribe((res: HttpResponse<ITeam[]>) => (this.teams = res.body || []));
    });
  }

  updateForm(player: IPlayer): void {
    this.editForm.patchValue({
      id: player.id,
      name: player.name,
      nickName: player.nickName,
      shirtNumber: player.shirtNumber,
      position: player.position,
      teamId: player.teamId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const player = this.createFromForm();
    if (player.id !== undefined) {
      this.subscribeToSaveResponse(this.playerService.update(player));
    } else {
      this.subscribeToSaveResponse(this.playerService.create(player));
    }
  }

  private createFromForm(): IPlayer {
    return {
      ...new Player(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      nickName: this.editForm.get(['nickName'])!.value,
      shirtNumber: this.editForm.get(['shirtNumber'])!.value,
      position: this.editForm.get(['position'])!.value,
      teamId: this.editForm.get(['teamId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlayer>>): void {
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

  trackById(index: number, item: ITeam): any {
    return item.id;
  }
}

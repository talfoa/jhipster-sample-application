import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScoreBoardUpdateComponent } from 'app/entities/score-board/score-board-update.component';
import { ScoreBoardService } from 'app/entities/score-board/score-board.service';
import { ScoreBoard } from 'app/shared/model/score-board.model';

describe('Component Tests', () => {
  describe('ScoreBoard Management Update Component', () => {
    let comp: ScoreBoardUpdateComponent;
    let fixture: ComponentFixture<ScoreBoardUpdateComponent>;
    let service: ScoreBoardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScoreBoardUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ScoreBoardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScoreBoardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScoreBoardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ScoreBoard(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ScoreBoard();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

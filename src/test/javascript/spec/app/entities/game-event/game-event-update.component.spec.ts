import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GameEventUpdateComponent } from 'app/entities/game-event/game-event-update.component';
import { GameEventService } from 'app/entities/game-event/game-event.service';
import { GameEvent } from 'app/shared/model/game-event.model';

describe('Component Tests', () => {
  describe('GameEvent Management Update Component', () => {
    let comp: GameEventUpdateComponent;
    let fixture: ComponentFixture<GameEventUpdateComponent>;
    let service: GameEventService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GameEventUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GameEventUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GameEventUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GameEventService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GameEvent(123);
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
        const entity = new GameEvent();
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

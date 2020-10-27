import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GameEventComponent } from 'app/entities/game-event/game-event.component';
import { GameEventService } from 'app/entities/game-event/game-event.service';
import { GameEvent } from 'app/shared/model/game-event.model';

describe('Component Tests', () => {
  describe('GameEvent Management Component', () => {
    let comp: GameEventComponent;
    let fixture: ComponentFixture<GameEventComponent>;
    let service: GameEventService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GameEventComponent],
      })
        .overrideTemplate(GameEventComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GameEventComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GameEventService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GameEvent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gameEvents && comp.gameEvents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

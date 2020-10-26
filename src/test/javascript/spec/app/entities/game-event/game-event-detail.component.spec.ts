import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { GameEventDetailComponent } from 'app/entities/game-event/game-event-detail.component';
import { GameEvent } from 'app/shared/model/game-event.model';

describe('Component Tests', () => {
  describe('GameEvent Management Detail Component', () => {
    let comp: GameEventDetailComponent;
    let fixture: ComponentFixture<GameEventDetailComponent>;
    const route = ({ data: of({ gameEvent: new GameEvent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [GameEventDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GameEventDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GameEventDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gameEvent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gameEvent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

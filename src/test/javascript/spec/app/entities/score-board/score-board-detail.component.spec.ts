import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScoreBoardDetailComponent } from 'app/entities/score-board/score-board-detail.component';
import { ScoreBoard } from 'app/shared/model/score-board.model';

describe('Component Tests', () => {
  describe('ScoreBoard Management Detail Component', () => {
    let comp: ScoreBoardDetailComponent;
    let fixture: ComponentFixture<ScoreBoardDetailComponent>;
    const route = ({ data: of({ scoreBoard: new ScoreBoard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScoreBoardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ScoreBoardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScoreBoardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load scoreBoard on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.scoreBoard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

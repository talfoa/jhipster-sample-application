import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScoreBoardComponent } from 'app/entities/score-board/score-board.component';
import { ScoreBoardService } from 'app/entities/score-board/score-board.service';
import { ScoreBoard } from 'app/shared/model/score-board.model';

describe('Component Tests', () => {
  describe('ScoreBoard Management Component', () => {
    let comp: ScoreBoardComponent;
    let fixture: ComponentFixture<ScoreBoardComponent>;
    let service: ScoreBoardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScoreBoardComponent],
      })
        .overrideTemplate(ScoreBoardComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScoreBoardComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScoreBoardService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ScoreBoard(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.scoreBoards && comp.scoreBoards[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

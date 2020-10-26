import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ScoreBoardService } from 'app/entities/score-board/score-board.service';
import { IScoreBoard, ScoreBoard } from 'app/shared/model/score-board.model';

describe('Service Tests', () => {
  describe('ScoreBoard Service', () => {
    let injector: TestBed;
    let service: ScoreBoardService;
    let httpMock: HttpTestingController;
    let elemDefault: IScoreBoard;
    let expectedResult: IScoreBoard | IScoreBoard[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ScoreBoardService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ScoreBoard(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, false, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ScoreBoard', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
          },
          returnedFromService
        );

        service.create(new ScoreBoard()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ScoreBoard', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            gamePart: 'BBBBBB',
            score: 'BBBBBB',
            scorePart: 'BBBBBB',
            hidden: true,
            hideTimer: true,
            remainingTimeInPeriod: 1,
            relativePlayerCount: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ScoreBoard', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            gamePart: 'BBBBBB',
            score: 'BBBBBB',
            scorePart: 'BBBBBB',
            hidden: true,
            hideTimer: true,
            remainingTimeInPeriod: 1,
            relativePlayerCount: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ScoreBoard', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

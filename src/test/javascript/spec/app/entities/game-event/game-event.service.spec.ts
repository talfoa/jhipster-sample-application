import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GameEventService } from 'app/entities/game-event/game-event.service';
import { IGameEvent, GameEvent } from 'app/shared/model/game-event.model';
import { GameEventType } from 'app/shared/model/enumerations/game-event-type.model';
import { TeamHomeOrAway } from 'app/shared/model/enumerations/team-home-or-away.model';

describe('Service Tests', () => {
  describe('GameEvent Service', () => {
    let injector: TestBed;
    let service: GameEventService;
    let httpMock: HttpTestingController;
    let elemDefault: IGameEvent;
    let expectedResult: IGameEvent | IGameEvent[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GameEventService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GameEvent(0, currentDate, 0, GameEventType.GOAL, TeamHomeOrAway.HOME, false, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            timeOfEventOccurence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GameEvent', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            timeOfEventOccurence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            timeOfEventOccurence: currentDate,
          },
          returnedFromService
        );

        service.create(new GameEvent()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GameEvent', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            gameEventId: 1,
            eventType: 'BBBBBB',
            team: 'BBBBBB',
            active: true,
            timeOfEventOccurence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            timeOfEventOccurence: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GameEvent', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            gameEventId: 1,
            eventType: 'BBBBBB',
            team: 'BBBBBB',
            active: true,
            timeOfEventOccurence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            timeOfEventOccurence: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GameEvent', () => {
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

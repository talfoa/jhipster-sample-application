import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MatchService } from 'app/entities/match/match.service';
import { IMatch, Match } from 'app/shared/model/match.model';

describe('Service Tests', () => {
  describe('Match Service', () => {
    let injector: TestBed;
    let service: MatchService;
    let httpMock: HttpTestingController;
    let elemDefault: IMatch;
    let expectedResult: IMatch | IMatch[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MatchService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Match(0, currentDate, 0, currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            matchDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Match', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            matchDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            matchDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Match()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Match', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            matchId: 1,
            matchDate: currentDate.format(DATE_TIME_FORMAT),
            cornerSending: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            matchDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Match', () => {
        const returnedFromService = Object.assign(
          {
            timeOfMessage: currentDate.format(DATE_TIME_FORMAT),
            matchId: 1,
            matchDate: currentDate.format(DATE_TIME_FORMAT),
            cornerSending: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeOfMessage: currentDate,
            matchDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Match', () => {
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

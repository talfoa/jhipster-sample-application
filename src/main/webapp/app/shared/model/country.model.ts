import { IRegion } from 'app/shared/model/region.model';

export interface ICountry {
  id?: number;
  countryName?: string;
  langage?: string;
  region?: IRegion;
}

export class Country implements ICountry {
  constructor(public id?: number, public countryName?: string, public langage?: string, public region?: IRegion) {}
}

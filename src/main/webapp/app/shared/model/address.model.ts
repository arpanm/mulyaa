import dayjs from 'dayjs';
import { IHub } from 'app/shared/model/hub.model';
import { IUser } from 'app/shared/model/user.model';

export interface IAddress {
  id?: number;
  streetAddress?: string | null;
  postalCode?: number | null;
  city?: string | null;
  stateProvince?: string | null;
  country?: string | null;
  createdBy?: string | null;
  lat?: number | null;
  lon?: number | null;
  mapLocation?: string | null;
  createdOn?: string | null;
  updatedOn?: string | null;
  updatedBy?: string | null;
  hub?: IHub | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IAddress> = {};

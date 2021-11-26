import dayjs from 'dayjs';
import { IActor } from 'app/shared/model/actor.model';
import { StockCategory } from 'app/shared/model/enumerations/stock-category.model';
import { RequirementStatus } from 'app/shared/model/enumerations/requirement-status.model';

export interface IRequirement {
  id?: number;
  category?: StockCategory | null;
  variant?: string | null;
  subVariant?: string | null;
  minPrice?: number | null;
  maxPrice?: number | null;
  quantityKg?: number | null;
  neededBy?: string | null;
  description?: string | null;
  createdOn?: string | null;
  createdBy?: string | null;
  updatedOn?: string | null;
  updatedBy?: string | null;
  status?: RequirementStatus | null;
  actor?: IActor | null;
}

export const defaultValue: Readonly<IRequirement> = {};
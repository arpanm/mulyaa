export enum OrderStatus {
  New = 'New',

  Paid = 'Paid',

  InTransit = 'InTransit',

  Delivered = 'Delivered',

  ReturnInitiatedInTransit = 'ReturnInitiatedInTransit',

  ReturnInitiatedAfterDelivery = 'ReturnInitiatedAfterDelivery',

  Returned = 'Returned',

  Cancelled = 'Cancelled',

  Refunded = 'Refunded',
}

import { TestBed } from '@angular/core/testing';

import { BillServiceService } from './bill-service.service';

describe('BillServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BillServiceService = TestBed.get(BillServiceService);
    expect(service).toBeTruthy();
  });
});

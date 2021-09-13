import { TestBed } from '@angular/core/testing';

import { MbsService } from './mbs.service';

describe('MbsService', () => {
  let service: MbsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MbsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

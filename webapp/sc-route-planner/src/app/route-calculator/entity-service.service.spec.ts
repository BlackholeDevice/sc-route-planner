import { TestBed } from '@angular/core/testing';

import { EntityService } from './entity.service';

describe('EntityServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EntityService = TestBed.get(EntityService);
    expect(service).toBeTruthy();
  });
});

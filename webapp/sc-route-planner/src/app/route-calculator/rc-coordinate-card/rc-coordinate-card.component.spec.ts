import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcCoordinateCardComponent } from './rc-coordinate-card.component';

describe('RcCoordinateCardComponent', () => {
  let component: RcCoordinateCardComponent;
  let fixture: ComponentFixture<RcCoordinateCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcCoordinateCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcCoordinateCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

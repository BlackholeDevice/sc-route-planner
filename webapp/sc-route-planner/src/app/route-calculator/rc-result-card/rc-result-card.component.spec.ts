import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcResultCardComponent } from './rc-result-card.component';

describe('RcResultCardComponent', () => {
  let component: RcResultCardComponent;
  let fixture: ComponentFixture<RcResultCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcResultCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcResultCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcCalculatorCardComponent } from './rc-calculator-card.component';

describe('RcCalculatorCardComponent', () => {
  let component: RcCalculatorCardComponent;
  let fixture: ComponentFixture<RcCalculatorCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcCalculatorCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcCalculatorCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

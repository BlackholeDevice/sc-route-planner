import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteCalculatorComponent } from './route-calculator.component';

describe('RouteCalculatorComponent', () => {
  let component: RouteCalculatorComponent;
  let fixture: ComponentFixture<RouteCalculatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteCalculatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcAsyncField } from './rc-async-field.component';

describe('RcFieldComponent', () => {
  let component: RcAsyncField;
  let fixture: ComponentFixture<RcAsyncField>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcAsyncField ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcAsyncField);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

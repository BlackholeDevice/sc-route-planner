import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcStaticFieldComponent } from './rc-static-field.component';

describe('RcPlaceholderComponent', () => {
  let component: RcStaticFieldComponent;
  let fixture: ComponentFixture<RcStaticFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcStaticFieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcStaticFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

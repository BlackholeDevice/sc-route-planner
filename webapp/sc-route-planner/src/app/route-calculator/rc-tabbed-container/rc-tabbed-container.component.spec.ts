import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RcTabbedContainerComponent } from './rc-tabbed-container.component';

describe('RcTabbedContainerComponent', () => {
  let component: RcTabbedContainerComponent;
  let fixture: ComponentFixture<RcTabbedContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RcTabbedContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RcTabbedContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

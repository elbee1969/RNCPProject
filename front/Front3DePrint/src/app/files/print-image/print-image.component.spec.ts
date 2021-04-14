import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PrintImageComponent } from './print-image.component';

describe('PrintImageComponent', () => {
  let component: PrintImageComponent;
  let fixture: ComponentFixture<PrintImageComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PrintImageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

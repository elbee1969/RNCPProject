import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListFilesComponent } from './admin-list-files.component';

describe('AdminListFilesComponent', () => {
  let component: AdminListFilesComponent;
  let fixture: ComponentFixture<AdminListFilesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListFilesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListFilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

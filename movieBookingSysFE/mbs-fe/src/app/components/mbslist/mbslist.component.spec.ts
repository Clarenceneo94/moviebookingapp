import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MbslistComponent } from './mbslist.component';

describe('MbslistComponent', () => {
  let component: MbslistComponent;
  let fixture: ComponentFixture<MbslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MbslistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MbslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

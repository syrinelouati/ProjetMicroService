import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampPlaceDetailsComponent } from './camp-place-details.component';

describe('CampPlaceDetailsComponent', () => {
  let component: CampPlaceDetailsComponent;
  let fixture: ComponentFixture<CampPlaceDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampPlaceDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampPlaceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigazioneComponent } from './navigazione.component';

describe('NavigazioneComponent', () => {
  let component: NavigazioneComponent;
  let fixture: ComponentFixture<NavigazioneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavigazioneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

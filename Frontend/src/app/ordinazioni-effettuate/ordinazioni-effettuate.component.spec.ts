import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdinazioniEffettuateComponent } from './ordinazioni-effettuate.component';

describe('OrdinazioniEffettuateComponent', () => {
  let component: OrdinazioniEffettuateComponent;
  let fixture: ComponentFixture<OrdinazioniEffettuateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdinazioniEffettuateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdinazioniEffettuateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

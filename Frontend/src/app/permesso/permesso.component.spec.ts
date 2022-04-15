import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermessoComponent } from './permesso.component';

describe('PermessoComponent', () => {
  let component: PermessoComponent;
  let fixture: ComponentFixture<PermessoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PermessoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PermessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

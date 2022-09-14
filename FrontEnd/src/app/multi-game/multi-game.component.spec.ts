import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiGameComponent } from './multi-game.component';

describe('MultiGameComponent', () => {
  let component: MultiGameComponent;
  let fixture: ComponentFixture<MultiGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultiGameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultiGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

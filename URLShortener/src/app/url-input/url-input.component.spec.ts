import { ComponentFixture, TestBed } from '@angular/core/testing';

import { URLInputComponent } from './url-input.component';

describe('URLInputComponent', () => {
  let component: URLInputComponent;
  let fixture: ComponentFixture<URLInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [URLInputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(URLInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

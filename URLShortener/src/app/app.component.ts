import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {URLInputComponent} from './url-input/url-input.component';
import {provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, URLInputComponent],

  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'URLShortener';
}

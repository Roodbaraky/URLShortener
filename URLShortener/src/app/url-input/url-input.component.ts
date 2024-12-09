import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {environment} from '../../environments/environtment';

@Component({
  selector: 'app-url-input',
  templateUrl: './url-input.component.html',
  standalone: true,
  imports: [
    MatLabel, MatFormField, MatInput, FormsModule, NgIf
  ],
  styleUrl: './url-input.component.scss'
})
export class URLInputComponent {
  longURL: string='';
  shortURL: string='';
  apiURL:string= environment.apiUrl;


  constructor(private http: HttpClient) {

  }

  generateShortURL() {

    console.log(`Generate shortURL from ${this.longURL}`)
    this.http.post<{ shortURL: string }>(window.location.href, {rawURL: this.longURL})
      .subscribe({
        next: (response) => {
          this.shortURL = response.shortURL;
        },
        error: (error) => {
          console.error('Error shortening URL:', error);
          alert('Failed to shorten the URL. Please try again.');
        }
      });
  }

  copyToClipboard() {

    navigator.clipboard.writeText(window.location.href+this.shortURL).then(r => {
      console.log("Copied to clipboard 2")
    })

  }


}

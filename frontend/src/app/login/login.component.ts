import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  constructor(
    private service: UserService,
    private router: Router,
    private dataService: DataService
  ) {}

  showLoader: boolean = false;
  errorReceived : boolean = false;
  errorMessage : string = '';

  errors = {
    emailAddress: { hasErrors: false, message: '' },
    password: { hasErrors: false, message: '' },
  };

  showPassword: boolean = false;

  controlPasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  loginForm = new FormGroup({
    emailAddress: new FormControl('hamza.hamdan@hotmail.com'),
    password: new FormControl('123$Qwert'),
  });

  handleRegisterForm() {
    this.errors = {
      emailAddress: { hasErrors: false, message: '' },
      password: { hasErrors: false, message: '' },
    };

    let hasErrors = false;

    if (this.loginForm.value.emailAddress!.trim() == '') {
      this.errors.emailAddress.message = 'Email address is required';
      hasErrors = true;
    }

    if (this.loginForm.value.password!.trim() == '') {
      this.errors.password.message = 'Password field is required';
      hasErrors = true;
    }

    if (!hasErrors) {
      this.showLoader = true;

      this.service
        .authenticateUser({
          emailAddress: this.loginForm.value.emailAddress!,
          password: this.loginForm.value.password!,
        })
        .subscribe({
          next: (response) => {
            if (response.items.authenticated) {
              this.router.navigate(['/personal']);

              this.showLoader = false;
              this.dataService.setAuthenticated(true);
              this.dataService.setUsername(response.items.name);
            }
          },
          error: (error) => {
            this.showLoader = false;
            console.log(error.status);
            this.errorReceived = true;
            this.errorMessage = error.status;
          },
        });
    }
  }
}

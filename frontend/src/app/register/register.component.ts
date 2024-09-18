import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  errors = {
    firstName: { hasErrors: false, message: [''] },
    lastName: { hasErrors: false, message: [''] },
    emailAddress: { hasErrors: false, message: [''] },
    password: { hasErrors: false, message: [''] },
    confirmPassword: { hasErrors: false, message: [''] },
  };

  registerForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    emailAddress: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
  });

  handleRegisterForm() {
    this.errors = {
      firstName: { hasErrors: false, message: [''] },
      lastName: { hasErrors: false, message: [''] },
      emailAddress: { hasErrors: false, message: [''] },
      password: { hasErrors: false, message: [''] },
      confirmPassword: { hasErrors: false, message: [''] },
    };

    let hasErrors = false;

    if (
      /^([a-zA-Z]){3,8}$/.test(this.registerForm.value.firstName!.trim()) ===
      false
    ) {
      this.errors.firstName.message.push('Incorrect first name regex');
      hasErrors = true;
    }

    if (
      /^([a-zA-Z]){3,8}$/.test(this.registerForm.value.lastName!.trim()) ===
      false
    ) {
      this.errors.lastName.message.push('Incorrect last name regex');
      hasErrors = true;
    }

    if (
      /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(
        this.registerForm.value.emailAddress!.trim()
      ) === false
    ) {
      this.errors.emailAddress.message.push('Incorrect email address regex');
      hasErrors = true;
    }

    if (
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@.#$!%*?&^])[A-Za-z\d@.#$!%*?&]{8,15}$/.test(
        this.registerForm.value.password!.trim()
      ) === false
    ) {
      this.errors.password.message.push('Incorrect password regex');
      hasErrors = true;
    }

    if (
      this.registerForm.value.password!.trim() !==
      this.registerForm.value.confirmPassword!.trim()
    ) {
      this.errors.confirmPassword.message.push('Passwords do not match');
      hasErrors = true;
    }

    if (hasErrors) {
      console.log('errors exist');
    } else {
      console.log('send request to the server');

      this.service
        .createUser(
          new User(
            0,
            this.registerForm.value.firstName!,
            this.registerForm.value.lastName!,
            this.registerForm.value.emailAddress!,
            this.registerForm.value.password!
          )
        )
        .subscribe({
          next: (data) => {
            console.log(data);
          },
          error: (error) => {
            console.log(error);
            this.errorReceived = true;
          },
        });
    }
  }

  errorReceived : boolean = false;

  constructor(private service: UserService) {}

  showPassword: boolean = false;
  showConfirmPassword: boolean = false;

  controlPasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  controlConfirmPasswordVisibility() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }
}

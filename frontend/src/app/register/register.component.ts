import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../model/user';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  file! : File | null;

  errors = {
    firstName: { hasErrors: false, message: '' },
    lastName: { hasErrors: false, message: '' },
    emailAddress: { hasErrors: false, message: '' },
    password: { hasErrors: false, message: '' },
    confirmPassword: { hasErrors: false, message: '' },
  };

  registerForm = new FormGroup({
    firstName: new FormControl('hamza'),
    lastName: new FormControl('hamdan'),
    emailAddress: new FormControl('hamza.hamdan' + Math.floor((Math.random() * 1000000) + 1) + '@hotmail.com'),
    password: new FormControl('123$Qwert'),
    confirmPassword: new FormControl('123$Qwert'),
    profilePicture: new FormControl(this.file)
  });

  showLoader : boolean = false;

  handleRegisterForm() {
    this.errors = {
      firstName: { hasErrors: false, message: '' },
      lastName: { hasErrors: false, message: '' },
      emailAddress: { hasErrors: false, message: '' },
      password: { hasErrors: false, message: '' },
      confirmPassword: { hasErrors: false, message: '' },
    };

    let hasErrors = false;

    if (
      /^([a-zA-Z]){3,8}$/.test(this.registerForm.value.firstName!.trim()) ===
      false
    ) {
      this.errors.firstName.message = 'Incorrect first name regex';
      hasErrors = true;
    }

    if (
      /^([a-zA-Z]){3,8}$/.test(this.registerForm.value.lastName!.trim()) ===
      false
    ) {
      this.errors.lastName.message = 'Incorrect last name regex';
      hasErrors = true;
    }

    if (
      /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(
        this.registerForm.value.emailAddress!.trim()
      ) === false
    ) {
      this.errors.emailAddress.message = 'Incorrect email address regex';
      hasErrors = true;
    }

    if (
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@.#$!%*?&^])[A-Za-z\d@.#$!%*?&]{8,15}$/.test(
        this.registerForm.value.password!.trim()
      ) === false
    ) {
      this.errors.password.message = 'Incorrect password regex';
      hasErrors = true;
    }

    if (this.registerForm.value.password!.trim() == '' && this.registerForm.value.confirmPassword!.trim() == '') {
      this.errors.confirmPassword.message = 'This field is required';
      hasErrors = true;
    }

    if (this.registerForm.value.password!.trim() !== this.registerForm.value.confirmPassword!.trim()) {
      this.errors.confirmPassword.message = 'Passwords do not match';
      hasErrors = true;
    }


    if (hasErrors) {
      console.log('errors exist');
    } else {
      console.log('send request to the server');

      this.showLoader = true;
      
      this.service
        .createUser(
          new User(
            0,
            this.registerForm.value.firstName!,
            this.registerForm.value.lastName!,
            this.registerForm.value.emailAddress!,
            this.registerForm.value.password!,
            ['USER_ROLE'],
            this.registerForm.value.profilePicture!
          )
        )
        .subscribe({
          next: (data) => {
            console.log(data);
            this.router.navigate(["/login"]);
            this.showLoader = false;
          },
          error: (error) => {
            console.log(error);
            this.errorReceived = true;
            this.router.navigate(["/register"]);
            this.showLoader = false;
          }
        });
    }
  }

  errorReceived : boolean = false;

  constructor(private service: UserService, private router : Router) {}

  showPassword: boolean = false;
  showConfirmPassword: boolean = false;

  controlPasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  controlConfirmPasswordVisibility() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  onFileChange(e : any) {
    if(e.target.files){
      this.file = e.target.files[0];
      console.log(this.registerForm.value.profilePicture)
    }
  }
}

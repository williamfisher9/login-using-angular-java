import {Component, ElementRef, ViewChild} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  errors = {
    emailAddress: { hasErrors: false, message: [''] },
    password: { hasErrors: false, message: [''] }
  };

  showPassword : boolean = false;

  controlPasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  loginForm = new FormGroup({
    emailAddress: new FormControl(''),
    password: new FormControl(''),
  });

  handleRegisterForm() {
    this.errors = {
      emailAddress: { hasErrors: false, message: [''] },
      password: { hasErrors: false, message: [''] }
    };

    let hasErrors = false;

    if(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.loginForm.value.emailAddress!.trim()) === false) {
      this.errors.emailAddress.message.push('Incorrect email address regex');
      hasErrors = true;
    }

    if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@.#$!%*?&^])[A-Za-z\d@.#$!%*?&]{8,15}$/.test(this.loginForm.value.password!.trim()) === false) {
      this.errors.password.message.push('Incorrect password regex');
      hasErrors = true;
    }

    if(hasErrors){
      console.log("errors exist")
    } else {
      console.log("send login request to the server")

      //this.service.getUsers().subscribe(data => {this.users = data, console.log(data)});
      this.service.authenticateUser({emailAddress: this.loginForm.value.emailAddress!, password: this.loginForm.value.password!})
    }


  }

  users : User[] = [];

  constructor(private service : UserService) { }


}

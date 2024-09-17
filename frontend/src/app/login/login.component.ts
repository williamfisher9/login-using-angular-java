import {Component, ElementRef, ViewChild} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  handleBlur() {
    console.log(this.usernameInput);
  }

  handleFocus(field: string) {
    if (field === 'input') {
      this.usernameInput.nativeElement.style.transform = 'translate(-126px, -30px)';
      this.usernameInput.nativeElement.style.scale = '0.6';
    } else {
      this.passwordInput.nativeElement.style.transform = 'translate(-126px, -30px)';
      this.passwordInput.nativeElement.style.scale = '0.6';
    }
  }
  @ViewChild('usernameLabel') usernameInput!: ElementRef;
  @ViewChild('passwordLabel') passwordInput!: ElementRef;

  applyForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  handleFormSubmit() {
    console.log(this.applyForm.value.username);
    console.log(this.applyForm.value.password);
  }
}

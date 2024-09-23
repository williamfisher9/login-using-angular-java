import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PublicComponent } from './public/public.component';
import { PersonalComponent } from './personal/personal.component';

export const routes: Routes = [
    {path: "", component: LoginComponent},
    {path: "login", component: LoginComponent},
    {path: "register", component: RegisterComponent},
    {path: "public", component: PublicComponent},
    {path: "personal", component: PersonalComponent},
];

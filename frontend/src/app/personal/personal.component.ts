import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal',
  standalone: true,
  imports: [],
  templateUrl: './personal.component.html',
  styleUrl: './personal.component.css'
})
export class PersonalComponent implements OnInit{

  constructor(private http : HttpClient, private router : Router){}

  ngOnInit(): void {

    this.http.get("http://localhost:8080/api/v1/app/personal", {headers: {'Content-Type': 'application/json',
          'authorization': `Basic ${window.btoa("hamza.hamdan@hotmail.com:123$Qwert")}`}})
    .subscribe({
      next: (response) => {window.localStorage.setItem("token", window.btoa("hamza.hamdan@hotmail.com:123$Qwert"))},
      error:  () => this.router.navigate(['/public']),
    });
  }

}

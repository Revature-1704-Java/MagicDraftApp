import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../shared/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private currentUser:User = new User('','');

  constructor(private http : HttpClient) { }

  ngOnInit() {
  }

  private processLogin() {

    let myForm = new FormData();
    myForm.append('email', this.currentUser.email);
    myForm.append('password', this.currentUser.password);

    this.http.post('http://18.216.223.139:8090/login', myForm).subscribe(res => {
      console.log(res);
    });
  }

}

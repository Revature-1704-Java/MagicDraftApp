import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public currentEmail : string;
  public currentPwd : string;

  constructor(private http:HttpClient) {}
  ngOnInit() {

  }

  public processLogin() {

    let myForm = new FormData();
    myForm.append('email', this.currentEmail);
    myForm.append('password', this.currentPwd);

    this.http.post('http://18.216.223.139:8090/login', myForm).subscribe(res => {
      console.log(res);
    });
  }
}

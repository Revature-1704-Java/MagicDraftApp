import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../shared/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  public currentEmail: string;
  public currentPwd: string;

  constructor(public login: LoginService, public router: Router) { }

  ngOnInit() {
  }

  public doLogin() {
    this.login.processLogin(this.currentEmail, this.currentPwd);
    this.login.setMyEmail(this.currentEmail);
  }

  public doRegister() {
    this.login.processRegistration(this.currentEmail, this.currentPwd);
  }

  public doLogout() {
    this.login.processLogout();
  }

  public home() {
     this.router.navigateByUrl('')
  }
}

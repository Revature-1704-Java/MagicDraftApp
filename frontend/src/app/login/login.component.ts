import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
      document.getElementById('login').addEventListener('click', this.validateLogin);
  }

  validateLogin() {
    let self = this.http;
    let user : string = (<HTMLInputElement>document.getElementById('username')).value;
    let pass : string = (<HTMLInputElement>document.getElementById('password')).value;
    let loginInfo : User = {email:user, password:pass};
    console.log(this);
    console.log(this.http);
    self.post('http://18.216.223.139:8090/login', loginInfo).subscribe(
      res => {
        console.log(res)
      }
    )

  }

}

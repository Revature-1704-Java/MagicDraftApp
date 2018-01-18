import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../shared/user';

@Injectable()
export class LoginService {
  public loggedIn : boolean;
  public loggedInUser : User;

  private currentEmail : string;
  private currentPwd : string;

  constructor(private http : HttpClient) {
    this.loggedInUser = {email: ''};
    this.loggedIn = false;
  }

  public processLogin(email:string, password:string) : boolean{

    let myForm = new FormData();
    myForm.append('email', email);
    myForm.append('password', password);

    this.http.post<User>('http://18.218.13.19:8090/login', myForm).subscribe(res => {
      if(res !== null) {
        this.loggedIn = true;
        console.log(res.email);
        this.loggedInUser.email = res.email;
        return true;
      }
    });
    return false;
  }

  public processRegistration(email:string, password:string) {
    let myForm = new FormData();
    myForm.append('email', email);
    myForm.append('password', password);

    this.http.post('http://18.218.13.19:8090/signup', myForm).subscribe(res => {
      console.log(res);
    });
  }

  public processLogout() {
    this.http.post('http://18.218.13.19:8090/logout', this.loggedInUser).subscribe(res => {
      console.log(res);
    });
    this.loggedIn = false;
    this.currentEmail = null;
    this.currentPwd = null;
    this.loggedInUser.email = null;
  }

  public setMyEmail(loggedInUserEmail: string) {
    this.loggedInUser.email = loggedInUserEmail;
  }

  public getMyEmail(): string {
    return this.loggedInUser.email;
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../shared/user';

@Injectable()
export class LoginService {
  public loggedIn : boolean;
  public loggedInUser : User;

  private currentEmail : string;
  private currentPwd : string;

  constructor(public http : HttpClient, public router : Router) {
    this.loggedInUser = {email: '', decks : []};
    this.loggedIn = false;
  }

  public processLogin(email:string, password:string) : boolean{

    let myForm = new FormData();
    myForm.append('email', email);
    myForm.append('password', password);

    this.http.post<User>('http://18.218.13.19:8090/login', myForm).subscribe(res => {
      if(res !== null) {
        this.loggedIn = true;
        console.log(res);
        this.loggedInUser.email = res.email;
        this.loggedInUser.decks = res.decks;
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

  public viewPastDeck(deckId : number) {
    console.log(this.loggedInUser.decks[deckId]);
    let deckArray = JSON.stringify([]);
    let temp = JSON.parse(deckArray)
    for(let i = 0; i < this.loggedInUser.decks[deckId].cards.length; i++) {
      console.log(this.loggedInUser.decks[deckId].cards[i].name);
    }
    deckArray = JSON.stringify(temp);


    sessionStorage.deck = JSON.stringify(deckArray);
    this.router.navigateByUrl('/summary')
  }

}

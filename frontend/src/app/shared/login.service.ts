import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../shared/user';

@Injectable()
export class LoginService {
  public loggedIn: boolean;
  public loggedInUser: User;

  private currentEmail: string;
  private currentPwd: string;

  constructor(public http: HttpClient, public router: Router) {
    this.loggedInUser = { email: '', decks: [] };
    this.loggedIn = false;
  }

  public processLogin(email: string, password: string): boolean {
    let myForm = new FormData();
    myForm.append('email', email);
    myForm.append('password', password);

    this.http.post<User>('http://18.218.13.19:8090/login', myForm).subscribe(res => {
      if (res !== null) {
        this.loggedIn = true;
        this.loggedInUser.email = res.email;
        this.loggedInUser.decks = res.decks;
        return true;
      }
    });
    return false;
  }

  public processRegistration(email: string, password: string) {
    let myForm = new FormData();
    myForm.append('email', email);
    myForm.append('password', password);

    this.http.post('http://18.218.13.19:8090/signup', myForm);
  }

  public processLogout() {
    this.http.post('http://18.218.13.19:8090/logout', this.loggedInUser);
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

  public viewPastDeck(deckId: number) {
    let deckArray = []
    for (let i = 0; i < this.loggedInUser.decks[deckId].cards.length; i++) {
      deckArray.push(this.loggedInUser.decks[deckId].cards[i].name);
    }
    sessionStorage.deck = JSON.stringify(deckArray);
    this.router.navigateByUrl('/summary')
  }
}

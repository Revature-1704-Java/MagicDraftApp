import { Component, OnInit } from '@angular/core';
import { LoginService } from '../shared/login.service';
declare let jquery: any;
declare let $: any;

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {
  constructor(public login: LoginService) { }

  ngOnInit() {
  }

  public getDeckId(deck : any) {
    for (let i = 0; i < this.login.loggedInUser.decks.length; i++) {
      if(deck == this.login.loggedInUser.decks[i]) {
        return i;
      }
    }
  }

  public pastDeck(deck : any) {
      this.login.viewPastDeck(this.getDeckId(deck));
  }
}

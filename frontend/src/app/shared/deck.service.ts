import { Injectable } from '@angular/core';

@Injectable()
export class DeckService {

  user_deck:any;

  constructor() {
    this.user_deck = "something"
  }

  setUserDeck(val : any){
    this.user_deck = val;
  }
  getUserDeck(val: any){
    return this.user_deck;
  }

}

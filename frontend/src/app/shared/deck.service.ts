import { Injectable } from '@angular/core';

@Injectable()
export class DeckService {

  public user_deck:any;

  constructor() {
    this.user_deck = "something"
  }

}

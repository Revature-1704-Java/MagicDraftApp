import { Deck } from './deck';

export interface User {
  email : string;
  decks : Deck[];
}

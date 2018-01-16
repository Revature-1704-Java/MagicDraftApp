import {Injectable} from '@angular/core';
import {Card} from './card';

@Injectable()
export class ApiAccessService {

  getCards(): Card[] {
    return cards;
  }

  getCardById(cardId: number): Card {
    return cards.find(p => p.id === cardId);
  }


}

export const cards = [
  {
    'id': 1,
    'title': 'Card1',
    'price': 15,
    'rating': 'E',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['Action']
  },
  {
    'id': 2,
    'title': 'Card2',
    'price': 1000,
    'rating': 'E',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['Action']
  },
  {
    'id': 3,
    'title': 'Card3',
    'price': 24.99,
    'rating': 'M',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['Horror']
  },
  {
    'id': 4,
    'title': 'Card4',
    'price': 15.99,
    'rating': 'E',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['Action, Horor']
  },
  {
    'id': 5,
    'title': 'Card5',
    'price': 4.99,
    'rating': 'T',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['RPG']
  },

  // adding more cards to test newly-added scroll bar!
  {
    'id': 6,
    'title': 'Card6',
    'price': 9.99,
    'rating': 'T',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['RPG']
  },
  {
    'id': 7,
    'title': 'Card7',
    'price': 12.99,
    'rating': 'T',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['RPG']
  },
  {
    'id': 8,
    'title': 'Card8',
    'price': 14.99,
    'rating': 'T',
    'shortDescription': 'This is a short description',
    'description': 'Lorem ipsum',
    'categories': ['RPG']
  }
];

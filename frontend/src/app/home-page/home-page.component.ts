import {Component, OnInit} from '@angular/core';
import {Card} from '../shared/card';
import {ApiAccessService} from '../shared/api-access.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {

  cards: Card[] = [];
  constructor(private cardService: ApiAccessService) {}

  ngOnInit() {
    this.cards = this.cardService.getCards();
  }

}


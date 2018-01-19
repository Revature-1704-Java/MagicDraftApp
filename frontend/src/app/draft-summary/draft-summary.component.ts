import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CardResponseTemplate } from '../shared/card-response-template';

@Component({
  selector: 'app-draft-summary',
  templateUrl: './draft-summary.component.html',
  styleUrls: ['./draft-summary.component.css']
})

export class DraftSummaryComponent implements OnInit {
  public deckImages: string[];
  constructor(public router: Router, public http: HttpClient) { }

  ngOnInit() {
    this.deckImages = [''];
    this.getImagesAgain();
  }

  public goHome() {
    this.router.navigateByUrl('/');
  }

  public getImagesAgain() {
    let deck = JSON.parse(sessionStorage.deck);
    for (let i = 0; i < deck.length; i++) {
      console.log(deck[i]);
      this.http.get<CardResponseTemplate>('https://api.magicthegathering.io/v1/cards?name=' + deck[i]).subscribe(res => {
        let options = res.cards;
        for (let x = 0; x < options.length; x++) {
          if (deck[i] === options[x].name && options[x].hasOwnProperty('imageUrl')) {
            this.deckImages.push(options[x].imageUrl);
            break;
          }
        }
      });
    }
    console.log(this.deckImages);
  }
}

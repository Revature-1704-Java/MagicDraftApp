import {Component, OnInit} from '@angular/core';
import {cards} from '../shared/api-access.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  cardList = cards;
  constructor() {}

  ngOnInit() {
  }

}

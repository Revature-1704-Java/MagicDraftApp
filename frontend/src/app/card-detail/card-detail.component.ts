import {Component, OnInit, Input} from '@angular/core';
import {Card} from '../shared/card';

@Component({
  selector: 'app-card-detail',
  templateUrl: './card-detail.component.html',
  styleUrls: ['./card-detail.component.css']
})
export class CardDetailComponent {

  constructor() {}

  @Input() card: Card;

}

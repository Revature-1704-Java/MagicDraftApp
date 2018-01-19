import {Component, OnInit} from '@angular/core';
import { LoginService } from '../shared/login.service';
declare let jquery:any;
declare let $ :any;

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {

  constructor(public login : LoginService) {}

  ngOnInit() {
  }

  public pastDeck() {
    console.log(this);
    this.login.viewPastDeck(1);
  }

}

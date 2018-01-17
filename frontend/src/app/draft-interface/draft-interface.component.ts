import {Component, OnInit} from '@angular/core';
import {DeckService} from '../shared/deck.service';
declare let jquery:any;
declare let $ :any;

@Component({
  selector: 'app-draft-interface',
  templateUrl: './draft-interface.component.html',
  styleUrls: ['./draft-interface.component.css']
})
export class DraftInterfaceComponent implements OnInit {



  constructor(private ds: DeckService) {}

  public draftCard(event: any) {
    $(event.target).hide();
    // console.log("hello");
  }

  ngOnInit() {
    let xhttp = new XMLHttpRequest();
    let pack_flag = 1;
    xhttp.open("POST","http://18.218.13.19:8090/generate/pack/players/2", true);
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        let json = xhttp.responseText;
        let obj = JSON.parse(json);
        let pack = obj[0];
        for(let i = 0; i < pack.length; i++){
          console.log(pack[i].name);
          grabImage(pack[i].name, i);
        }
        // let something = document.cookie;
        // window.alert(something);
      }
    };
    xhttp.send();
  }
}
function setCookie(cname, cvalue){
  document.cookie = cname + "=" + cvalue;
}
function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie;
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

// function draftCard(cardName:string){
//   // if(getCookie("deck") == ""){
//   //   setCookie("deck", cardName);
//   // } else {
//   //   setCookie("deck", getCookie("deck") += cardName);
//   // }
//   // getCookie()
//   //update current pack
//   //store pick into temporary cookie
//   //Update other pack
//   //change view to next pack
//   //display deck
//   console.log(cardName);
// }

function grabImage(cardName:string, id:int){
  let xhttp = new XMLHttpRequest();
  let url = "https://api.magicthegathering.io/v1/cards?name=" + cardName;
  let answer = "";

  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let json = this.responseText;
      let obj = JSON.parse(json);
      let image = obj.cards;
      let image_link = "";
      for(let x = 0; x < image.length; x++){
        if(image[x].hasOwnProperty('imageUrl')){
          image_link = image[x].imageUrl;
        }
      }

      document.getElementById(id).src = image_link;
      document.getElementById(id).name = cardName;
      // console.log(image_link);
      // document.getElementById("demo").innerHTML += ("<img src=\"" + image_link + "\">");
      // // console.log(document.getElementById(0).id);
      // document.getElementById(id).src = image_link;
      // document.getElementById(id).name = cardName;
      // document.getElementById(id).addEventListener("click", draftCard(cardName));
      // document.getElementById(id).addEventListener("click", draftCard(cardName));
      // document.getElementById("demo").innerHTML += ("<img id=\"" + id + "\" src=\"" + image_link + "\" width=\"150px\" height=\"200px\" (click)=\"draftCard($event)\">");

      // document.getElementById("demo").getElementById(id).addEventListener("click", draftCard(cardName));
      // document.getElementById("demo").innerHTML += ("<img id=\"" + id + "\" src=\"" + image_link + "\" width=\"150px\" height=\"200px\" onclick=\"draftCard(cardName)\">");
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

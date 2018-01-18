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

  constructor(public ds: DeckService) {}

  public draftCard(event: any) {

    let current_pack = JSON.parse(sessionStorage.current);
    let next_pack = JSON.parse(sessionStorage.nextPack);
    for(let index = 0; index < current_pack.length; index++){
      if(current_pack[index].name == event.target.name){
        current_pack.splice(index, 1);
      }
    }
    //delete card from computer's pack and set the current pack to this pack
    sessionStorage.current = JSON.stringify(computerPick());

    // console.log(sessionStorage.current);
    //swap our pack with the computer's
    sessionStorage.nextPack = JSON.stringify(current_pack);

    // console.log(sessionStorage.current);
    // sessionStorage.deck = event.target.name;
    if(sessionStorage.pickcounter != 14) {
      displayCard();
      sessionStorage.pickcounter = parseInt(sessionStorage.pickcounter) + 1;
      //last pick so we can move on to the next round
    } else {
      if(sessionStorage.round == 1) {
        sessionStorage.round = parseInt(sessionStorage.round) + 1;
        sessionStorage.current = sessionStorage.pack_three;
        sessionStorage.nextPack = sessionStorage.pack_four;
        resetImage();
        displayCard();
        sessionStorage.pickcounter = 0;
      } else if  (sessionStorage.round == 2){
        sessionStorage.round = parseInt(sessionStorage.round) + 1;
        sessionStorage.current = sessionStorage.pack_five;
        sessionStorage.nextPack = sessionStorage.pack_six;
        resetImage();
        displayCard();
        sessionStorage.pickcounter = 0;

      } else {
        //go to result page
        sendSaveDeck();
      }
    }
    displayDeck(event.target.src);
    let temp_deck = JSON.parse(sessionStorage.deck);
    temp_deck.push(event.target.name);
    sessionStorage.deck = JSON.stringify(temp_deck);
  }

  ngOnInit() {
    let xhttp = new XMLHttpRequest();
    sessionStorage.pickcounter = 0;
    sessionStorage.round = 1;
    let deck_array = [];
    sessionStorage.deck = JSON.stringify(deck_array);
    xhttp.open("POST","http://18.218.13.19:8090/generate/pack/players/2", true);
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        let json = xhttp.responseText;
        let obj = JSON.parse(json);

        sessionStorage.pack_one = JSON.stringify(obj[0]);
        sessionStorage.pack_two = JSON.stringify(obj[1]);
        sessionStorage.pack_three = JSON.stringify(obj[2]);
        sessionStorage.pack_four = JSON.stringify(obj[3]);
        sessionStorage.pack_five = JSON.stringify(obj[4]);
        sessionStorage.pack_six = JSON.stringify(obj[5]);

        // console.log(obj[0]);
        // console.log(obj[1]);
        // console.log(obj[2]);
        // console.log(obj[3]);
        // console.log(obj[4]);
        // console.log(obj[5]);






        sessionStorage.current = JSON.stringify(obj[0]);
        sessionStorage.nextPack = JSON.stringify(obj[1]);
        let pack = obj[0];
        for(let i = 0; i < pack.length; i++){
          console.log(pack[i].name);
          grabImage(pack[i].name, i);
        }
      }
    };
    xhttp.send();
  }
}

function displayDeck (image:string) {
  document.getElementById("display_deck").innerHTML += ("<img src=\"" + image + "\" width=\"75px\" height=\"100px\">");
}

function computerPick (){
  console.log("*");
  console.log("*");
  console.log("*");
  console.log("*");
  let next_pack = JSON.parse(sessionStorage.nextPack);
  let min = next_pack[0].draftValue;
  let delete_index = 0;
  for(let index = 0; index < next_pack.length; index++){
    if(next_pack[index].draftValue < min){
      min = next_pack[index].draftValue;
      console.log("---------------------deleting card-----------------------------------");
      delete_index = index;
    }
  }
  next_pack.splice(delete_index, 1);
  return next_pack;
}

function displayCard() {
  let cp = JSON.parse(sessionStorage.current);
  console.log(cp)
  for(let index = 0; index < cp.length; index++){
    grabImage(cp[index].name,index);
  }
  for(let i=cp.length; i < 15; i++){
    document.getElementById(i + "").style.visibility = "hidden";
  }
}
function resetImage() {
  for(let index = 0; index < 15; index++){
    document.getElementById(index + "").style.visibility = "visible";
  }
}


function grabImage(cardName:string, id:number){
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

      document.getElementById(id + "").setAttribute("src", image_link);
      document.getElementById(id + "").setAttribute("name", cardName);

      // document.getElementById("demo").getElementById(id).addEventListener("click", draftCard(cardName));
      // document.getElementById("demo").innerHTML += ("<img id=\"" + id + "\" src=\"" + image_link + "\" width=\"150px\" height=\"200px\" onclick=\"draftCard(cardName)\">");
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}
function sendSaveDeck(){
  let xhttp = new XMLHttpRequest();
  xhttp.open("POST","http://18.218.13.19:8090/save/deck", true);
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      /*TODO*/
    }
    xhttp.send();
}

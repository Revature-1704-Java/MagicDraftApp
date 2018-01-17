import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-draft-interface',
  templateUrl: './draft-interface.component.html',
  styleUrls: ['./draft-interface.component.css']
})
export class DraftInterfaceComponent implements OnInit {

  constructor() {}


  ngOnInit() {
    let xhttp = new XMLHttpRequest();
    let pack_flag = 1;
    xhttp.open("POST","http://18.216.223.139:8090/generate/pack/players/2", true);
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        let json = xhttp.responseText;
        // document.getElementById("demo").innerHTML = json;
        let obj = JSON.parse(json);
        // sessionStorage.setItem()
        let pack = obj[0];
        for(let i = 0; i < pack.length; i++){
          console.log(pack[i].name);
          grabImage(pack[i].name);
        }
      }
    };
    xhttp.send();
  }
}
function grabImage(cardName:string){
  let xhttp = new XMLHttpRequest();
  let url = "https://api.magicthegathering.io/v1/cards?name=" + cardName;
  let answer = "";

  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let json = this.responseText;
      let obj = JSON.parse(json);
      let image = obj.cards;
      var image_link = "";
      for(let x = 0; x < image.length; x++){
        if(image[x].hasOwnProperty('imageUrl')){
          image_link = image[x].imageUrl;
        }
      }
      console.log(image_link);
      // document.getElementById("demo").innerHTML += ("<img src=\"" + image_link + "\">");

      document.getElementById("demo").innerHTML += ("<img src=\"" + image_link + "\" width=\"150px\" height=\"200px\" ondblclick = draftCard()>");
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

function draftCard(){

}

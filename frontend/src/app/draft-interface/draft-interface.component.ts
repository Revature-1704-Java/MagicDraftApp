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
    xhttp.open("POST","http://18.216.223.139:8090/generate/pack/players/2", true);
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState == 4 && xhttp.status == 200) {
        let json = xhttp.responseText;
        // document.getElementById("demo").innerHTML = json;
        let obj = JSON.parse(json);
        for(let i =0; i < obj.length; i++){
          let temp = obj[i];
          for(let j =0; j < obj[i].length; j++){
            console.log(temp[j].name);
            let imgurl = "<img src=\"" + grabImage(temp[j].name) + "\">";
            console.log(imgurl);
            document.getElementById("demo").innerHTML += ("<img src=\"" + grabImage(temp[j].name) + ">");
          }
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
      answer = image[0].imageUrl;
      console.log(answer);
      return answer;
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

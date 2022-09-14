import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Game} from "./Models/game.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-game-screen',
  templateUrl: './game-screen.component.html',
  styleUrls: ['./game-screen.component.css']
})

export class GameScreenComponent implements OnInit {
  form: FormGroup;

  game: Game;

  button = {
    disabled: false
  }

  constructor(public fb: FormBuilder, private http: HttpClient, private rt: Router) {
    this.form = this.fb.group({
      username: '',
    });
    this.game = {
      username: "",
      slots: [
        "cherry",
        "lemon",
        "waterLemon"
      ],
      userCredits: null,
      loan: null,
      withdraw: null
    }
  }

  ngOnInit() {
    if (localStorage.getItem("username")) {
      this.game.username = localStorage.getItem("username") ?? "";
    }
    if (!localStorage.getItem("token")) {
      this.rt.navigate(['/login']);
    }
  }


  submitForm(type: string) {
    const formData: any = new FormData();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    formData.append('username', localStorage.getItem("username") ?? "");
    this.button.disabled = true;
    this.http
      .post(`/api/${type}`, Object.fromEntries(formData), {headers: headers})
      .subscribe({
        next: (response: (any)) => {
          let tempSlots = response.slots ?? this.game.slots;
          this.game = response;
          this.game.username = localStorage.getItem("username") ?? response.username;
          this.game.slots = tempSlots;
          this.button.disabled = false;
        },
        error: (error) => console.log(error),
      });
  }

}


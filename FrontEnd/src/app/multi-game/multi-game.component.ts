import {Component, OnInit} from '@angular/core';
import {MultiGame} from "./Models/multi-game.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {GamePlay} from "./Models/game-play.model";
import {WebsocketService} from "../services/websocket.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-multi-game',
  templateUrl: './multi-game.component.html',
  styleUrls: ['./multi-game.component.css']
})
export class MultiGameComponent implements OnInit {

  form: FormGroup

  game: MultiGame;

  play: GamePlay;

  playingWith: string;

  createButtons = {
    disabled: false
  }

  gameButtons = {
    disabled: false
  }

  isPlayer2: boolean;

  constructor(public fb: FormBuilder, private rt: Router, private ws: WebsocketService, private auth: AuthService) {
    this.form = this.fb.group({
      gameId: '',
    });
    this.game = {
      gameId: "",
      player1: null,
      player2: null,
      choice1: 0,
      choice2: 0,
      gameStatus: 0,
      winner: ""
    }
    this.play = {
      type: "",
      choice: 0,
      gameId: "",
      winner: ""
    }
    this.playingWith = "";
    this.isPlayer2 = false;
  }

  ngOnInit(): void {
    this.auth.redirect();
    if (this.game.gameId !== "") {
      this.createButtons.disabled = true;
    } else {
      this.gameButtons.disabled = true;
    }
  }

  buttonOperations() {
    this.createButtons.disabled = true;
    this.gameButtons.disabled = false;
  }

  createGame() {
    this.game.player1 = localStorage.getItem('username');
    this.ws.createRoom(this.game.player1 ?? "");
    this.ws.onRoomResponse().subscribe((res: MultiGame) => {
      this.game = res;
      this.buttonOperations();
      alert("Created.");
    });
  }

  connectGame() {
    this.isPlayer2 = true;
    this.game.player2 = localStorage.getItem('username');
    this.game.player1 = "";
    this.ws.connectRoom(this.game.player2, this.form.value.gameId);
    this.ws.onConnectRoom().subscribe((res: MultiGame) => {
      this.game = res;
      this.buttonOperations();
      alert("Connected.");
    });

  }

  connectToRandom() {
    this.isPlayer2 = true;
    this.game.player2 = localStorage.getItem('username');
    this.game.player1 = "";
    this.ws.connectRandomRoom(this.game.player2);
    this.ws.onRandomConnected().subscribe((res: MultiGame) => {
      this.game = res;
      this.buttonOperations();
      alert("Connected.");
    });
  }

  playTheGame(choice: number) {
    let playerType: string;
    if (this.isPlayer2) {
      playerType = "PLAYER2";
    } else {
      playerType = "PLAYER1";
    }
    this.gameButtons.disabled = true;
    this.play = {
      type: playerType,
      choice: choice,
      gameId: this.game.gameId,
      winner: ""
    }
    this.ws.sendPlay(this.play);
    this.ws.onPlayResponse().subscribe((res: MultiGame) => {
      this.game = res;
      let winner = res.winner;
      if (this.game.winner === "PLAYER1") {
        winner = this.game.player1 ?? "";
      } else if (this.game.winner === "PLAYER2") {
        winner = this.game.player2 ?? "";
      }
      this.play.winner = winner;
      this.gameButtons.disabled = true;
    });
  }


}

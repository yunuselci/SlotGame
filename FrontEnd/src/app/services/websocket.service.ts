import {Injectable, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {io, Socket} from "socket.io-client";
import {Message} from "../chat/Models/message.model";
import {GamePlay} from "../multi-game/Models/game-play.model";
import {MultiGame} from "../multi-game/Models/multi-game.model";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket: Socket;

  private token: string | null;

  constructor() {
    this.token = localStorage.getItem('token');
    this.socket = io('http://localhost:3000');
    this.sendToken();
  }

  sendToken() {
    console.log("here");
    this.socket.emit('token', this.token);
  }

  sendMessage(msg: Message) {
    this.socket.emit('message', msg);
  }

  sendPlay(play: GamePlay) {
    this.socket.emit('play', play);
  }

  createRoom(username: string) {
    this.socket.emit('createRoom', username);
    this.socket.emit('token', localStorage.getItem('token'));

  }

  connectRoom(username: string | null, gameId: string) {
    this.socket.emit('connectRoom', username, gameId);
  }

  connectRandomRoom(username: string | null) {
    this.socket.emit('connectRandom', username);
  }

  onNewMessage() {
    return new Observable<Message>(observer => {
      this.socket.on('message', (data: Message) => observer.next(data));
    });
  }

  onRoomResponse() {
    return new Observable<MultiGame>(observer => {
      this.socket.on('roomCreated', (data: MultiGame) => observer.next(data));
    });
  }

  onConnectRoom() {
    return new Observable<MultiGame>(observer => {
      this.socket.on('connectRoom', (data: MultiGame) => observer.next(data));
    });
  }

  onRandomConnected() {
    return new Observable<MultiGame>(observer => {
      this.socket.on('randomConnected', (data: MultiGame) => observer.next(data));
    });
  }

  onPlayResponse() {
    return new Observable<MultiGame>(observer => {
      this.socket.on('play', (data: MultiGame) => observer.next(data));
    });
  }

}

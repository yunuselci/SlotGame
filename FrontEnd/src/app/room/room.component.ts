import {Component, OnInit} from '@angular/core';
import {MultiGame} from "../multi-game/Models/multi-game.model";
import {WebsocketService} from "../services/websocket.service";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  rooms: MultiGame[] = [];

  constructor(private ws: WebsocketService) {
  }

  ngOnInit(): void {
    this.ws.onRoomResponse().subscribe((data: MultiGame) => {
      this.rooms.push(data);
    });
  }
}

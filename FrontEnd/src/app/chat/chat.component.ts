import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Message} from "./Models/message.model";
import {Router} from "@angular/router";
import {WebsocketService} from "../services/websocket.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  form: FormGroup;

  chatMessage: Message;

  msgArray: Message[] = [];

  constructor(public fb: FormBuilder, private rt: Router, private ws: WebsocketService, private auth: AuthService) {
    this.form = this.fb.group({
      content: '',
    });
    this.chatMessage = {
      content: '',
      sender: '',
    };
  }

  ngOnInit(): void {
    this.auth.redirect();
    this.chatMessage.sender = this.auth.getUsername() ?? '';
    this.ws.onNewMessage().subscribe((msg: Message) => {
      this.msgArray.push(msg);
    });
  }

  submitForm() {
    this.chatMessage.content = this.form.get('content')?.value;
    this.ws.sendMessage(this.chatMessage);
    this.form.reset();
  }
}

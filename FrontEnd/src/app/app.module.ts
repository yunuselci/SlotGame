import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GameScreenComponent} from './game-screen/game-screen.component';

import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {ChatComponent} from './chat/chat.component';
import { MultiGameComponent } from './multi-game/multi-game.component';
import { RoomComponent } from './room/room.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { GameSelectorComponent } from './game-selector/game-selector.component';

@NgModule({
  declarations: [
    AppComponent,
    GameScreenComponent,
    RegisterComponent,
    LoginComponent,
    ChatComponent,
    MultiGameComponent,
    RoomComponent,
    GameSelectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

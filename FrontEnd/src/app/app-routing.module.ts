import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {GameScreenComponent} from './game-screen/game-screen.component';
import {LoginComponent} from './login/login.component';
import {MultiGameComponent} from "./multi-game/multi-game.component";
import {RoomComponent} from "./room/room.component";
import {GameSelectorComponent} from "./game-selector/game-selector.component";

const routes: Routes = [
  {path: '', component: GameSelectorComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'slot-game', component: GameScreenComponent},
  {path: 'multi-game', component: MultiGameComponent},
  {path: 'room', component: RoomComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

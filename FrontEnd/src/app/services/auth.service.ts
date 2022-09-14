import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private username: string | null;

  constructor(private rt: Router) {
    this.username = localStorage.getItem('username');
  }

  redirect() {
    if (this.username == null) {
      this.rt.navigate(['/login']);
    } else {
      this.rt.navigate([this.rt.url]);
    }
  }

  getUsername() {
    return this.username;
  }

}

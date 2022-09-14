import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Login} from "./Models/login.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  form: FormGroup;

  login: Login;

  error?: string;

  constructor(public fb: FormBuilder, private http: HttpClient, private rt: Router) {
    this.form = this.fb.group({
      username: '',
      password: '',
    });
    this.login = {
      token: '',
      user: {
        username: "",
        credits: 0,
        loan: 0,
        withdraw: 0
      },
    }
    delete this.error;
  }

  submitForm() {
    const formData: any = new FormData();
    formData.append('username', this.form.get('username')?.value);
    formData.append('password', this.form.get('password')?.value);
    this.http
      .post(`/api/login`, Object.fromEntries(formData))
      .subscribe({
        next: (response: (any)) => {
          this.login = response;
          localStorage.setItem("token", this.login.token);
          localStorage.setItem("username", this.login.user.username);
          setTimeout(() => {
            localStorage.removeItem("token");
            localStorage.removeItem("username");
          }, 300000);
          if (!!this.login.token) {
            this.rt.navigate(['/']);
          }
        },
        error: () => this.error = "Invalid username or password",
      });
  }

}

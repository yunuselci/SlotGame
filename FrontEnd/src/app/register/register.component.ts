import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Register} from "./Models/register.model";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {

  form: FormGroup;

  register: Register;

  error: string[];

  constructor(public fb: FormBuilder, private http: HttpClient, private rt: Router) {
    this.form = this.fb.group({
      username: '',
      password : '',
    });
    this.register = {
      message: '',
    }
    this.error = [];
  }

  submitForm() {
    const formData: any = new FormData();
    formData.append('username', this.form.get('username')?.value);
    formData.append('password', this.form.get('password')?.value);
    this.http
      .post(`/api/register`, Object.fromEntries(formData))
      .subscribe({
        next: (response: (any)) => {
          this.register = response;
        },
        error: (error: (any)) => {
          let errors = error.error.errors;
          for (let error of errors) {
            this.error.push(error.field + " " + error.defaultMessage);
          }
        }
      });
  }

}

import { Component, OnInit } from '@angular/core';
import {UserInternal} from "../../models/UserInternal";
import {ApiService} from "../../../shared/api.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserInternal = {
    name: null,
    email: null,
    password: null,
    privileges: 0
  }
  apiService: ApiService;

  route: Router;

  constructor(apiService: ApiService, route: Router) {
    this.apiService = apiService;
    this.route = route;
  }

  ngOnInit(): void {
  }

  submitLogin() {
    this.apiService.loginUser(this.user).subscribe(
      res => {
        if (res !== null) {
          let userSession : any = {
            name : res.name,
            privileges : res.privileges
          }
          localStorage.setItem("inUser", JSON.stringify(userSession));
          location.pathname ="";
        }
        else {
          alert("This user / password combination is wrong")
        }

      },
      err => {
        alert("Login failed")
      }
    )
  }
}

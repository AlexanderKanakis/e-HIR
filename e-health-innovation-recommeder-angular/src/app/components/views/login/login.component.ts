import { Component, OnInit } from '@angular/core';
import {User} from "../../models/User";
import {ApiService} from "../../../shared/api.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = {
    name: null,
    email: null,
    password: null,
    countryID: null,
    departmentID: null,
    occupationID: null,
    privileges: 1
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
          localStorage.setItem("user", JSON.stringify(userSession));
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

import { Component, OnInit } from '@angular/core';
import { ViewEncapsulation} from "@angular/core";
import {MatMenu} from "@angular/material/menu";
import {MatMenuContent} from "@angular/material/menu";


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NavigationComponent implements OnInit {

  user: any = {
    name: null,
    privileges: 0
  };


  constructor() {
  }

  ngOnInit(): void {
    let sessionUser = JSON.parse(localStorage.getItem("inUser"));

    if (sessionUser !== null) {
      this.user = sessionUser;
    }

  }

  logOut() {
    this.user = {
      name: null,
      privileges: 0
    };
    localStorage.setItem("inUser", JSON.stringify(this.user));
    location.pathname="";

  }

}

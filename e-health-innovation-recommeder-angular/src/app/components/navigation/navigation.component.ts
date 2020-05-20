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
    this.user = JSON.parse(localStorage.getItem("user"));
  }

  logOut() {
    this.user = {
      name: null,
      privileges: 0
    };
    localStorage.setItem("user", JSON.stringify(this.user));
    location.pathname="";

  }

}

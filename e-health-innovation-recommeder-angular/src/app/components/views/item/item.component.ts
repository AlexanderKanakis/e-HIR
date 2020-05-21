import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../../shared/api.service";
import {Item} from "../../models/Item";
import {faTrashAlt} from "@fortawesome/free-solid-svg-icons";
import {faPlus} from "@fortawesome/free-solid-svg-icons";
import {UserInternal} from "../../models/UserInternal";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  sub: any;
  route: ActivatedRoute;
  apiService: ApiService;
  itemId: number;
  itemLinkList: string[];
  item: Item = {
    id: null,
    name: null,
    description: null,
    links: null,
    ratingSum: null,
    ratingUsers: null,
    image: null,
    imageId: null
  };

  // Icons
  faTrashAlt = faTrashAlt;
  faPlus = faPlus;

  // User
  inUser: UserInternal = {
    name: "",
    email: "",
    password: "",
    privileges: 0
  }

  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;

  constructor(route:ActivatedRoute, apiService: ApiService) {
    this.route = route;
    this.apiService = apiService;
  }

  ngOnInit(): void {
    this.inUser = JSON.parse(localStorage.getItem("inUser"));
    this.sub = this.route.params.subscribe(params => {
      this.itemId = params['id'];
      this.selectItemById(this.itemId);

    });

  }

  selectItemById(id: number) {
    this.apiService.getItemById(id).subscribe(
      res => {
        this.item = res;
        this.itemLinkList = this.item.links.split(", ")
        this.checkForItemImage();
      },
      err => {
        alert("cannot fetch item");
      }
    );
  }

  deleteItemStart() {
    this.apiService.deleteItemTagRelation(this.itemId).subscribe(
      res => {
        this.deleteItemImage();
      }
    )
  }

  deleteItemImage() {
    this.apiService.deleteItemImage(this.item.imageId).subscribe(
      res => {
        this.deleteItem();
      }
    )
  }

  deleteItem() {
    this.apiService.deleteItemById(this.itemId).subscribe(
      res => {
        location.assign("../../library")
      }
    )
  }

  checkForItemImage() {
      if (this.item.imageId != 0) {
        this.getImageOfItem()
      }
  }

  getImageOfItem() {
    this.apiService.getItemImage(this.item.imageId)
      .subscribe(
        res => {
          this.retrieveResponse = res;
          this.base64Data = this.retrieveResponse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
          this.item.image = this.retrievedImage;
        },
        err => {
          alert(`Could not get image: ${this.item.imageId}`)
        }
      );
  }

}

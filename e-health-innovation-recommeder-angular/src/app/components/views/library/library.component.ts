import { Component, OnInit } from '@angular/core';
import {Item} from "../../models/Item";
import {ApiService} from "../../../shared/api.service";

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {

  itemList: Item[] = [];
  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    if (false) {
      this.filterItemsViaUserSurvey();
    }
    else {
      this.getAllItems();
    }
  }

  public getAllItems() {
    this.apiService.getAllItems().subscribe(
      succ => {
        this.itemList = succ;
        this.checkForItemImage();
        this.createSortDescription();
      },
      err => {
        alert("Could not get items")
      }
    )
  }

  getImageOfItem(item: Item) {
    this.apiService.getItemImage(item.imageId)
      .subscribe(
        res => {
          this.retrieveResponse = res;
          this.base64Data = this.retrieveResponse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
          item.image = this.retrievedImage;
        },
        err => {
          alert(`Could not get image: ${item.imageId}`)
        }
      );
  }

  checkForItemImage() {
    for (let item of this.itemList) {
      if (item.imageId != 0) {
        this.getImageOfItem(item)
      }
    }
  }

  createSortDescription() {
    for (let item of this.itemList) {
      let shortDesc = `${item.description.substring(0,225)}...`;
      item.description = shortDesc;
    }
  }

  filterItemsViaUserSurvey() {

  }

  highlightItem(id: number) {
    document.getElementById(id.toString()).style.backgroundColor = "rgba(255,255,255,0.1)";
  }

  unhighlightItem(id: number) {
    document.getElementById(id.toString()).style.backgroundColor = "rgba(255,255,255,0.05)";
  }

  deleteItem(id: number) {
    alert();
  }

  }


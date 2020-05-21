import { Component, OnInit } from '@angular/core';
import {Item} from "../../../models/Item";
import {Tag} from "../../../models/Tag";
import {ApiService} from "../../../../shared/api.service";
import {faTimesCircle} from "@fortawesome/free-solid-svg-icons";
import {faAngleDown} from "@fortawesome/free-solid-svg-icons";
import {faAngleUp} from "@fortawesome/free-solid-svg-icons";
import {faPlusCircle} from "@fortawesome/free-solid-svg-icons";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  // Icons
  faTimesCircle = faTimesCircle;
  faAngleDown = faAngleDown;
  faAngleUp = faAngleUp;
  faPlusCircle = faPlusCircle;

  // View Models
  view: Item = {
    id: 0,
    name: "",
    description: "",
    links: "",
    image: null,
    imageId: 0,
    ratingUsers: 0,
    ratingSum: 0
  }

  newTag: Tag = {
    id: 0,
    name: ""
  }

  itemId: number;

  //Img File from input type=file
  selectedFile: File;

  // Tag Lists
  tagList: Tag[] = [];
  totalTags: Tag[] = [];
  tagItemList: Tag[] = [];

  // Toggle Tag Container
  tagContainerToggle: boolean = false;

  //Get id
  sub: any;
  editItemRoute: ActivatedRoute;

  constructor(private apiService: ApiService, editItemRoute: ActivatedRoute) {
    this.editItemRoute = editItemRoute;
  }

  ngOnInit(): void {
    this.sub = this.editItemRoute.params.subscribe(params => {
      this.itemId = params['id'];
      this.selectItemById();
      this.selectItemTags()

    });
    this.getAllTags();
  }

  submitItem() {

    if (this.selectedFile) {
      this.updateImage();
    }
    else {
      this.updateItem();
    }

  }

  updateItem() {
    this.apiService.updateItemById(this.view, this.itemId).subscribe(
      res => {
        this.apiService.deleteItemTagRelation(this.itemId).subscribe(
          res => {
            this.updateItemTagRelations(this.itemId, 0);
          }
        )
      },
      err => {
        alert("error")
      })
  }

  updateImage() {
    const uploadImageData = new FormData();
    uploadImageData.append('image', this.selectedFile, this.selectedFile.name)
    this.apiService.updateItemImage(this.view.imageId, uploadImageData).subscribe(
      res => {
        this.view.imageId = res;
        this.updateItem()
      },
      err => {
        alert("Could not upload image")
      })

  }

  updateItemTagRelations(itemId: number, tagCounter: number) {
    let counter: number = tagCounter;
    this.apiService.createItemTagRelation(itemId, this.tagItemList[counter]).subscribe(
      res => {
        counter++;
        if (counter >= this.tagItemList.length) {
          location.assign(`../../item/${this.itemId}`);
        }
        else {
          this.updateItemTagRelations(itemId, counter);
        }

      });
  }

  checkForLinkSeparation(links: string) {
    this.view.links ='';
  }

  onFileSelected(img) {
    this.selectedFile = <File>img.target.files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
      document.getElementById("img-display").style.backgroundImage ='url("' + reader.result + '")';
      document.getElementById("img-display").style.backgroundColor = 'black';
    }
    if (this.selectedFile) {
      reader.readAsDataURL(this.selectedFile);
    } else {
    }
  }

  getAllTags() {
    this.apiService.getAllTags().subscribe(
      succ => {
        this.totalTags = succ
        this.tagList = this.totalTags;
      },
      err => {
        alert("Could not get items")
      }
    )
  }

  selectTagForItem(newTag: Tag) {
    for (let tag of this.tagItemList) {
      if (tag.id == newTag.id) {
        return false;
      }
    }
    this.tagItemList.push(newTag);
    return true;
  }

  removeTagFromItem(selectedTag: Tag) {
    this.tagItemList = this.tagItemList.filter(obj => obj !== selectedTag);
  }

  addNewTag() {
    if (this.newTag.name != '') {
      this.apiService.addNewTag(this.newTag).subscribe(
        succ => {
          this.newTag.name = '';
          this.getAllTags()
        }
      )
    }
  }

  filterTags() {
    this.tagList = this.totalTags
    let self = this;
    if (this.newTag.name != "") {
      this.tagList = this.tagList.filter(function (element) {
        return element.name.indexOf(self.newTag.name) !== -1;
      })
    }
  }

  selectItemById() {
    this.apiService.getItemById(this.itemId).subscribe(
      res => {
        this.view = res
      },
      err => {
        alert("Cannot get item");
      }
    )
  }

  selectItemTags() {
    this.apiService.getItemTags(this.itemId).subscribe(
      res => {
        this.tagItemList = res;
      }
    )
  }
}

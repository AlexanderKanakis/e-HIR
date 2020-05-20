import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../../../shared/api.service";
import {Item} from "../../../models/Item";
import {Tag} from "../../../models/Tag";
import { faTimesCircle} from '@fortawesome/free-solid-svg-icons';
import {faAngleDown} from "@fortawesome/free-solid-svg-icons";
import {faPlusCircle} from "@fortawesome/free-solid-svg-icons";
import {faAngleUp} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css'],
})
export class CreateItemComponent implements OnInit {

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
    shortDesc: ""
  }

  newTag: Tag = {
    id: 0,
    name: ""
  }

  //Img File from input type=file
  selectedFile: File;

  // Tag Lists
  tagList: Tag[] = [];
  totalTags: Tag[] = [];
  tagItemList: Tag[] = [];

  // Toggle Tag Container
  tagContainerToggle: boolean = false;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.getAllTags();
  }

  submitItem() {

    if (this.selectedFile) {
      this.uploadImage();
    }
    else {
      this.uploadItem();
    }

  }

  uploadItem() {
    this.apiService.addItem(this.view).subscribe(
        res => {
          this.uploadItemTagRelations(res, 0);
        },
        err => {
          alert("error")
        })
  }

  uploadImage() {
    const uploadImageData = new FormData();
    uploadImageData.append('image', this.selectedFile, this.selectedFile.name)
    this.apiService.addItemImage(uploadImageData).subscribe(
        res => {
          this.view.imageId = res;
          this.uploadItem()
        },
        err => {
          alert("Could not upload image")
        })

  }

  uploadItemTagRelations(itemId: number, tagCounter: number) {
    let counter: number = tagCounter;
    this.apiService.createItemTagRelation(itemId, this.tagItemList[counter]).subscribe(
      res => {
        counter++;
        if (counter >= this.tagItemList.length) {
          location.reload();
        }
        else {
          this.uploadItemTagRelations(itemId, counter);
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



}


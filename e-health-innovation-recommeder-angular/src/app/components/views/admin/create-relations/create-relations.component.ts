import { Component, OnInit } from '@angular/core';
import {Country} from "../../../models/Country";
import {Department} from "../../../models/Department";
import {Occupation} from "../../../models/Occupation";
import {ApiService} from "../../../../shared/api.service";
import {Tag} from "../../../models/Tag";

@Component({
  selector: 'app-create-relations',
  templateUrl: './create-relations.component.html',
  styleUrls: ['./create-relations.component.css']
})
export class CreateRelationsComponent implements OnInit {

  // View Models
  selectedCountry: Country = {
    id: 0,
    name: ''
  }

  selectedDepartment: Department = {
    id: 0,
    name: ''
  }

  selectedOccupation: Occupation = {
    id: 0,
    name: ''
  }

  selectedTagCountry: Tag = {
    id: 0,
    name: ''
  }

  selectedTagDepartment: Tag = {
    id: 0,
    name: ''
  }

  selectedTagOccupation: Tag = {
    id: 0,
    name: ''
  }

  // Lists
  countryList : Country[];
  departmentList : Department[];
  occupationList : Occupation[];
  tagList: Tag[];



  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.getAllCountries();
    this.getAllDepartments();
    this.getAllOccupations();
    this.getAllTags();
  }

  getAllCountries() {
    this.apiService.getAllCountries().subscribe(
      res => {
        this.countryList = res;
      }
    );
  }

  getAllDepartments() {
    this.apiService.getAllDepartments().subscribe(
      res => {
        this.departmentList = res;
      }
    );
  }

  getAllOccupations() {
    this.apiService.getAllOccupations().subscribe(
      res => {
        this.occupationList = res;
      }
    )
  }

  getAllTags() {
    this.apiService.getAllTags().subscribe(
      res => {
        this.tagList = res;
      }
    )
  }

  submitCountryTagRelation() {
    console.log(this.selectedCountry.id)
    this.apiService.createCountryTagRelation(this.selectedCountry.id, this.selectedTagCountry).subscribe(
      res => {
        console.log("completed");
      }
    )
  }

  submitDepartmentTagRelation() {
    this.apiService.createDepartmentTagRelation(this.selectedDepartment.id, this.selectedTagDepartment).subscribe(
      res => {
        console.log("completed");
      }
    )
  }

  submitOccupationTagRelation() {
    this.apiService.createOccupationTagRelation(this.selectedOccupation.id, this.selectedTagOccupation).subscribe(
      res => {
        console.log("completed");
      }
    )
  }

}

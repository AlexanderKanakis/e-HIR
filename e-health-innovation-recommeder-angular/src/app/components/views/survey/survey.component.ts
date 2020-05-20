import { Component, OnInit } from '@angular/core';
import {Country} from "../../models/Country";
import {Department} from "../../models/Department";
import {ApiService} from "../../../shared/api.service";
import {Occupation} from "../../models/Occupation";


@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {



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

  // Lists
  countryList : Country[];
  departmentList : Department[];
  occupationList : Occupation[];

  //Toggle buttons
  step1Toggle: boolean =false;
  step2Toggle: boolean =false;
  step3Toggle: boolean =false;
  step4Toggle: boolean =false;


  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllCountries();
    this.getAllDepartments();
    this.getAllOccupations();
  }

  getAllCountries() {
    this.apiService.getAllCountries().subscribe(
      res => {
        this.countryList = res;
      }
    );
  }

  getCountryNameById(id: number) {
    if (id !== null) {
      for (let country of this.countryList) {
        if (id == country.id) {
          return country.name;
        }
      }
    }
  }

  getAllDepartments() {
    this.apiService.getAllDepartments().subscribe(
      res => {
        this.departmentList = res;
      }
    );
  }

  getDepartmentNameById(id: number) {
    if (id !== null) {
      for (let dep of this.departmentList) {
        if (id == dep.id) {
          return dep.name;
        }
      }
    }
  }

  getAllOccupations() {
    this.apiService.getAllOccupations().subscribe(
      res => {
        this.occupationList = res;
      }
    )
  }

}

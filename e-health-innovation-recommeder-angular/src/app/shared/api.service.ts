import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Item} from "../components/models/Item";
import {ItemImage} from "../components/models/ItemImage";
import {Tag} from "../components/models/Tag";
import {Country} from "../components/models/Country";
import {Department} from "../components/models/Department";
import {Occupation} from "../components/models/Occupation";
import {User} from "../components/models/User";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080/api/v1/';
  private allItemsUrl = `${this.baseUrl}/item`;
  private allTagsUrl = `${this.baseUrl}/tag`;
  private allUsersInternalUrl = `${this.baseUrl}/user/internal`;
  private allUsersExternalUrl = `${this.baseUrl}/user/external`;
  private allImagesUrl = `${this.baseUrl}/item-image`;
  private allCountriesUrl = `${this.baseUrl}country`;
  private allDepartmentsUrl = `${this.baseUrl}department`;
  private allOccupationsUrl = `${this.baseUrl}occupation`;

  constructor(private http: HttpClient) { }

  addItem(item: Item) : Observable<any> {
    return this.http.post(this.allItemsUrl, item)
  }
  getAllItems() : Observable<Item[]> {
    return this.http.get<Item[]>(this.allItemsUrl);
  }

  getAllTags() : Observable<Tag[]> {
    return this.http.get<Tag[]>(this.allTagsUrl);
  }

  addItemImage(image) {
    return this.http.post(this.allImagesUrl, image)
  }
  getItemImage(imageId) {
    return this.http.get<ItemImage>(`${this.allImagesUrl}/${imageId}`)
  }

  getItemById(id: number) : Observable<Item> {
    return this.http.get<Item>(`${this.allItemsUrl}/${id}`);
  }
  updateItemById(item: Item, id: number) : Observable<any> {
    return this.http.put<Item>(`${this.allItemsUrl}/${id}`, item);
  }
  deleteItemById(id: number) : Observable<any> {
    return this.http.delete<Item>(`${this.allItemsUrl}/${id}`);
  }

  createItemTagRelation(itemId: number, tag: Tag) {
    return this.http.post(`${this.allItemsUrl}/${itemId}/tags`, tag)
  }

  getItemTags(id : number) : Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.allItemsUrl}/${id}/tags`);
  }

  createCountryTagRelation(id: number, tag: Tag) {
    return this.http.post(`${this.allCountriesUrl}/${id}/tags`, tag)
  }

  getCountryTags(id : number) : Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.allCountriesUrl}/${id}/tags`);
  }

  createDepartmentTagRelation(departmentId: number, tag: Tag) {
    return this.http.post(`${this.allDepartmentsUrl}/${departmentId}/tags`, tag)
  }

  getDepartmentTags(id : number) : Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.allDepartmentsUrl}/${id}/tags`);
  }

  createOccupationTagRelation(occupationId: number, tag: Tag) {
    return this.http.post(`${this.allOccupationsUrl}/${occupationId}/tags`, tag)
  }

  getOccupationTags(id : number) : Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.allOccupationsUrl}/${id}/tags`);
  }

  addNewTag(tag: Tag) {
    return this.http.post(`${this.allTagsUrl}`, tag)
  }

  getAllCountries() {
    return this.http.get<Country[]>(this.allCountriesUrl);
  }

  getCountryById(id: number) {
    return this.http.get<Country>(`${this.allCountriesUrl}/${id}`);
  }

  getAllDepartments() {
    return this.http.get<Department[]>(this.allDepartmentsUrl);
  }

  getAllOccupations() {
    return this.http.get<Occupation[]>(this.allOccupationsUrl);
  }

  loginUser(user: User) {
    return this.http.post<User>(`${this.allUsersInternalUrl}/login`, user)
  }
}

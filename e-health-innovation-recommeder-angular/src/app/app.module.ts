import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { GraphViewComponent } from './components/views/graph-view/graph-view.component';
import { SurveyComponent } from './components/views/survey/survey.component';
import { LibraryComponent } from './components/views/library/library.component';
import { NotFoundComponent } from './components/views/not-found/not-found.component';
import { AboutComponent } from './components/views/about/about.component';
import {Router, RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateItemComponent } from './components/views/admin/create-item/create-item.component';
import {HttpClientModule} from "@angular/common/http";
import {EditorModule} from "@tinymce/tinymce-angular";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CreateRelationsComponent} from "./components/views/admin/create-relations/create-relations.component";
import { LoginComponent } from './components/views/login/login.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatMenuItem} from "@angular/material/menu";
import {MatMenuContent} from "@angular/material/menu";


const appRouteList: Routes = [
  {
    path: 'graph-view',
    component: GraphViewComponent
  },
  {
    path: 'library',
    component: LibraryComponent
  },
  {
    path: 'survey',
    component: SurveyComponent
  },
  {
    path: 'about',
    component: AboutComponent
  },
  {
    path: 'admin/create-item',
    component: CreateItemComponent
  },
  {
    path: 'admin/create-relations',
    component: CreateRelationsComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: SurveyComponent,
    pathMatch:'full'
  },
  {
    path: '**',
    component: NotFoundComponent,
  }

];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    GraphViewComponent,
    SurveyComponent,
    LibraryComponent,
    NotFoundComponent,
    AboutComponent,
    CreateItemComponent,
    CreateRelationsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRouteList),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    EditorModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    FontAwesomeModule,
    MatMenuModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

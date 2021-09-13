import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { AddmovieComponent } from './components/addmovie/addmovie.component';
import { MoviedetailsComponent } from './components/moviedetails/moviedetails.component';
import { MovielistComponent } from './components/movielist/movielist.component';
import { MbslistComponent } from './components/mbslist/mbslist.component';
// import { MbsformComponent } from './components/mbsform/mbsform.component';

@NgModule({
  declarations: [
    AppComponent,
    // AddmovieComponent,
    MoviedetailsComponent,
    MovielistComponent,
    MbslistComponent
    // MbsformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

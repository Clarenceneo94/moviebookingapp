import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { MbsformComponent } from './components/mbsform/mbsform.component';
import { MbslistComponent } from './components/mbslist/mbslist.component';
import { MoviedetailsComponent } from './components/moviedetails/moviedetails.component';
import { MovielistComponent } from './components/movielist/movielist.component';


// public static final url


const routes: Routes = [
  { path: '', redirectTo: 'mbs', pathMatch: 'full' },
  { path: 'mbs', component: MbslistComponent },
  // { path: 'mbs/form', component: MbsformComponent },
  { path: 'movies', component: MovielistComponent },
  { path: 'movies/updatemovie/:movieId', component: MoviedetailsComponent },

  // { path: 'seats', component: BookingComponent }
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

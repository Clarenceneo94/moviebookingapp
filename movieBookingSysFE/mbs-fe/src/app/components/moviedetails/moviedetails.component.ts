import { Component, OnInit } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/app/models/movie.model';

@Component({
  selector: 'app-moviedetails',
  templateUrl: './moviedetails.component.html',
  styleUrls: ['./moviedetails.component.css']
})
export class MoviedetailsComponent implements OnInit {

  currentMovie: Movie = {
    movieId: '',
    movieName: '',
    movieDesc: '',
    movieShowTime: ''
  };
  message = '';

  constructor(
    private moviesService: MoviesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getMovie(this.route.snapshot.params.movieId);
  }
  getMovie(id: any): void {
    this.moviesService.get(id)
      .subscribe(
        data => {
          this.currentMovie = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }


  updateMovie(): void {
    this.message = '';
    this.moviesService.update(this.currentMovie.movieId, this.currentMovie)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message ? response.message : 'Movie updated successfully!';
        },
        error => {
          console.log(error);
        });
        this.reloadCurrentRoute();
  }

  reloadCurrentRoute() {
    window.location.replace('/movies');
  }

  // deleteMovie(): void {
  //   this.message = '';
  //   this.moviesService.delete(this.currentMovie.movieId)
  //     .subscribe(
  //       response => {
  //         alert(response);
  //         console.log(response);
  //         this.router.navigate(['/movie']);
  //         this.message = response.message ? response.message : 'Movie deleted!';
  //       },
  //       error => {
  //         console.log(error);
  //       });
  // }
}

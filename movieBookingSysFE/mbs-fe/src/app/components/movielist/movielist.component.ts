import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/app/models/movie.model';
import { MoviesService } from 'src/app/services/movies.service';

@Component({
  selector: 'app-movielist',
  templateUrl: './movielist.component.html',
  styleUrls: ['./movielist.component.css']
})
export class MovielistComponent implements OnInit {
  //Getting List of all movies
  movies?: Movie[];
  currentMovie: Movie = {};
  currentIndex = -1;
  movieName = '';
  movieId: any;
  
  //Adding Movies
  movie: Movie = {
    movieName: '',
    movieDesc: '',
    movieShowTime: ''
  };
  submitted = false;


  // currentMovie: Movie = {
  //   movieId: '',
  //   movieName: '',
  //   movieDesc: '',
  //   movieShowTime: ''
  // };
  message = '';


  constructor(private movieService: MoviesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getMovies();
  }

  getMovies(): void {
    this.movieService.getAll()
      .subscribe(
        data => {
          this.movies = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.getMovies();
    this.currentMovie = {};
    this.currentIndex = -1;
  }

  setActiveMovie(movie: Movie, index: number): void {
    this.currentMovie = movie;
    this.currentIndex = index;
  }


  saveMovie(): void {
    const data = {
      movieName: this.movie.movieName,
      movieDesc: this.movie.movieDesc,
      movieShowTime: this.movie.movieShowTime
    };

    this.movieService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
        this.reloadCurrentRoute();
  }

  insertMovie(): void {
    this.submitted = false;
    this.movie = {
      movieName: '',
      movieDesc: '',
      movieShowTime: ''
    };

  }


  deleteMovie(movieid:any): void {
    this.message = '';
    this.movieService.delete(movieid)
      .subscribe(
        response => {
          alert(response);
          console.log(response);
          this.router.navigate(['/movies']);
          this.message = response.message ? response.message : 'Movie deleted!';
        },
        error => {
          console.log(error);
        });
        this.reloadCurrentRoute();
  }



  reloadCurrentRoute() {
    window.location.replace(this.router.url);
    let currentUrl = this.router.url;
    
    // alert(currentUrl);
    // alert(this.route)
    this.router.navigateByUrl('', {skipLocationChange: true}).then(() => {
        this.router.navigate([currentUrl]);
        console.log(currentUrl);
    });

  //   this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
  //     this.router.navigate(['Your actualComponent']);
  // }); 


  }




  // removeAllMovies(): void {
  //   this.movieService.deleteAll()
  //     .subscribe(
  //       response => {
  //         console.log(response);
  //         this.refreshList();
  //       },
  //       error => {
  //         console.log(error);
  //       });
  // }

  searchMovieByTitle(): void {
    this.currentMovie = {};
    this.currentIndex = -1;

    this.movieService.findByMovieName(this.movieName)
      .subscribe(
        data => {
          this.movies = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }


}

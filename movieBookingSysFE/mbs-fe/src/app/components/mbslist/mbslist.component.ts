import { Component, Input, OnInit } from '@angular/core';
import { Movie } from 'src/app/models/movie.model';
import { Seat } from 'src/app/models/seat.model';
import { MbsService } from 'src/app/services/mbs.service';
import { MoviesService } from 'src/app/services/movies.service';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-mbslist',
  templateUrl: './mbslist.component.html',
  styleUrls: ['./mbslist.component.css']
})
export class MbslistComponent implements OnInit {
  movies?: Movie[];
  message = "";
  seatData:any;
  seating:any;
  booking:any;

  //Adding Movies
  movie: Movie = {
    movieName: '',
    movieDesc: '',
    movieShowTime: ''
  };

  currentIndex=-1
  currentIndex2=-1
  currentSeats={}
  postId: any;
  errorMessage: any;
  data: any;
  
  constructor(private mbsService: MbsService, private movieService: MoviesService,private http: HttpClient) { }

  ngOnInit(): void {
    this.message = '';
    //Movie Id = 1
    this.getMovieDetails(1);
    this.getAllSeats();
  }


  
  seatArray = [] as  any;
  setActiveSeat(seat: Seat, index: number): void {
    this.currentSeats = seat;
    this.currentIndex = index;
    if(!this.find(this.currentSeats,this.seatArray)){
      this.seatArray.push(this.currentSeats);
    }
  }

  setDisableSeat(seat: Seat, index: number): void {
    this.currentSeats = seat;
    this.currentIndex2 = index;
    console.log(this.currentSeats);
    console.log("Current Index" + this.currentIndex2);
    
    this.seatArray.splice(this.currentIndex2,1);
    console.log(this.seatArray);
  }

  getSelectedSeats(seatArray:any){
    this.seatArray = seatArray
  }


  find(obj:any, list:any) {
      var i;
      for (i = 0; i < list.length; i++) {
          if (list[i] === obj) {
              return true;
          }
      }
      return false;
  }

  arrayObj:any
   
  jsonObject = {};
  onClickSubmit() {
    
    this.getSelectedSeats(this.seatArray);
    this.seatArray.forEach( (arrayObj: any) => {
      var x = arrayObj.Seat.seatId;
      var json = JSON.stringify({"seatId" : x});
      this.mbsService.book(JSON.parse(json))
      .subscribe(
        data => {
          this.data = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  });
 
  this.reloadCurrentRoute();
  }



  getAllSeats(): any {
    this.mbsService.getAllSeats()
      .subscribe(
        data => {
          this.seatData = data;
          // console.log(this.seatData);
          for (const obj in this.seatData) {
              this.seating = this.seatData[obj].Seat;
              this.booking = this.seatData[obj].Booking;
          }
        },
        error => {
          console.log(error);
        });
  }



  reloadCurrentRoute() {
    window.location.replace('/mbs');
  }

  goForm() {
    window.location.replace('/mbs/form');
  }




  //Get movie Id = 1
  getMovieDetails(movieId:any): void {
    this.movieService.get(movieId)
      .subscribe(
        data => {
          this.movie = data;
          //alert(data.movieDesc);
          // console.log(data);
        },
        error => {
          console.log(error);
        });
  }


}

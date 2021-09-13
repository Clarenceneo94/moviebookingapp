import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie.model';


const baseUrl = 'http://localhost:8080/mbs';


@Injectable({
  providedIn: 'root'
})
export class MbsService {

  constructor(private http: HttpClient) { }
  //for movie id = 1
  getAllSeats(): Observable<any> {
    return this.http.get<any>(`${baseUrl}/movieseating?movieId=1`);
  }


  book(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/book/booking`,data);
  }


}


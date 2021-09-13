import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie.model';


const baseUrl = 'http://localhost:8080/movie';


@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${baseUrl}/getmovies`);
  }

  get(id: any): Observable<Movie> {
    return this.http.get(`${baseUrl}/getmoviesbyid?movieId=${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/insertmovie`, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updatemovie/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deletemovie/${id}`);
  }

  // deleteAll(): Observable<any> {
  //   return this.http.delete(baseUrl);
  // }

  findByMovieName(movieName: any): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${baseUrl}/getmovies?movieName=${movieName}`);
  }


}


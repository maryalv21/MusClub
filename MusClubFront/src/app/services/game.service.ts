import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../Models/Game.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private readonly BASE_URL= 'http://localhost:8080/games';

  constructor(private http: HttpClient) { }

  createGame(game: Game): Observable<any> {
    return this.http.post(`${this.BASE_URL}`, game);
  }

  findGame(game: Game): Observable<any> {
    return this.http.get(`${this.BASE_URL}`);
  }

  findGameId(id:number): Observable<any> {
    return this.http.get(`${this.BASE_URL}/${id}`);
  }
}

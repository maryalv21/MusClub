import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../Models/Game.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private readonly BASE_URL= 'http://localhost:8080/games';
  private game:Game;

  constructor(private http: HttpClient) {
    this.game= new Game(0, '','', []);
   }

  createGame(game: Game): Observable<any> {
    return this.http.post(`${this.BASE_URL}`, game);
  }

  findAll(): Observable<Game[]> {
    return this.http.get<Game[]>(`${this.BASE_URL}`);
  }

  findById(id:number): Observable<Game> {
    return this.http.get<Game>(`${this.BASE_URL}/${id}`);
  }

  deleteGame(id:number): Observable<Game[]> {
    this.http.delete(`${this.BASE_URL}/${id}`);
    return this.findAll();
  }
}

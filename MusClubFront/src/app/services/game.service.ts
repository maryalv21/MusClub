import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../Models/Game.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private readonly BASE_URL= 'http://localhost:8080/games';
  private readonly API_URL= 'http://localhost:8080/usergame';
  private game:Game;

  constructor(private http: HttpClient) {
    this.game= new Game(0, '','', [], '', '', '');
   }

  findAll(): Observable<Game[]> {
    const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
    const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.get<Game[]>(`${this.BASE_URL}`);
  }

  findById(id:number): Observable<Game> {
    const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
    const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.get<Game>(`${this.BASE_URL}/${id}`);
  }

  findGameByUserId(id:number): Observable<Game[]> {
    const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
    const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.get<Game[]>(`${this.API_URL}/${id}`);
  }

  createGame(game: Game): Observable<any> {
    const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
    const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.post(`${this.BASE_URL}`, game);
  }

  updateGame(id:number, game:Game): Observable<any>  {
  const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
  const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.put<Game>(`${this.BASE_URL}/${id}`, game);
  }

  deleteGame(id:number): Observable<Game[]> {
  const username: string = JSON.parse(localStorage.getItem("currentUser") as string).username;
  const password: string = JSON.parse(localStorage.getItem("currentUser") as string).password;
    return this.http.delete<Game[]>(`${this.BASE_URL}/${id}`);
  }
}

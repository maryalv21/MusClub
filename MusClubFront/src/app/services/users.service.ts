import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from 'src/app/Models/Member.model';
import { Player } from 'src/app/Models/Player.model';


@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private readonly BASE_URL= 'http://localhost:8080/members';

  constructor(private http: HttpClient) {

  }

  register(member: Member): Observable<any> {
    return this.http.post("http://localhost:8080/members", member);
  }

  loginMember(email:string): Observable<any> {
      return this.http.get<any>(`${this.BASE_URL}/${email}`);
   }

  deleteMember(id: number): Observable<any> {
    return this.http.delete(`${this.BASE_URL}/${id}`)
  }

  updateMember(id: number, member: Member): Observable<any> {
    return this.http.put(`${this.BASE_URL}/${id}`, member)
  }

  loginPlayer(player: Player): Observable<any> {
   return this.http.post("http://localhost:8080/players", player)
   }


  }


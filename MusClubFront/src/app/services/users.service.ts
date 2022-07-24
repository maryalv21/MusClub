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
    return this.http.post(`${this.BASE_URL}`, member);
  }

  loginMember(email:string): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa(`${email}`));
      return this.http.get<any>(`${this.BASE_URL}/${email}`);
      //return this.http.get<Member>(`${this.BASE_URL}/login`, { headers: headers });
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


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

  isAuthenticated(): boolean {
    const token: string | null = localStorage.getItem('currentMember');
    return token !== null;
  }

  login(username: string, password: string): Observable<Member> {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa(`${username}:${password}`));

    return this.http.get<Member>(`${this.BASE_URL}/`, { headers: headers });
  }

  logout(): void {
    // Remove user from local storage to log user out
    localStorage.removeItem('currentMember');
  }

  register(member: Member): Observable<any> {
    return this.http.post(`${this.BASE_URL}`, member);
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


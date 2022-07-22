import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {



  constructor(private http: HttpClient) {

  }

    login(user: any): Observable<any> {
      const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Access-Control-Allow-Origin', '*');
      return this.http.post("http://localhost:8080/members", + user , {'headers':headers});

   }


  }


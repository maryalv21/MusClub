import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Member } from 'src/app/Models/Member.model';
import { AuthService } from 'src/app/services/auth.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  member: Member;

  @Input()
  password: number;
  @Input()
  email:string;



  username: string | null;
  id: number;
  showMember: boolean = false;
  profile: string;
  name: string;
  level:string;
  playerName: string;
  role: string;

  constructor(private usersService: UsersService,
    private authService: AuthService,
    private router: Router
     ) {
    this.username = '';
    this.member = new Member(0, '', 0, '', '', '', '', '');
    this.id= 0;
    this.name= '';
    this.email = '';
    this.password = 0;
    this.role = '';
    this.playerName= '';
    this.level = '';
    this.profile = 'View Stats';
  }

  ngOnInit(): void {
    // localStorage.getItem("currentUser");
    //this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.getDetails()
  }

  ngOnChanges(){
    this.getDetails()
  }

  getDetails(){
    this.usersService.loginMember(this.email).subscribe(
      dataResult => {
          console.log(dataResult)
          const id: number = dataResult[0].id;
          const name: string = dataResult[0].name;
          const playerName: string = dataResult[0].playerName;
          const email: string = dataResult[0].email;
          const level: string = dataResult[0].level;
      }
    )
  }

  logout(): void {
    console.log('logging out...');
    this.authService.logout();

    this.router.navigate(['/login']);

  }

}

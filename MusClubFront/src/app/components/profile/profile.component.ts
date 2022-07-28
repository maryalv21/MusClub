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

     username: string;

     member: Member;
     members: Member[];
     id: number;
     showMember: boolean = false;
     profile: string;
     name: string;
     level:string;
     playerName: string;
     role: string;
     email:string;

     @Input()
     password:string;

  constructor(private usersService: UsersService,
    private router: Router
     ) {
    this.username = '';
       this.members = [];
       this.member = new Member(0, '', '', '', '', '', '', '');
       this.id= 0;
       this.name= '';
       this.email = '';
       this.password = '';
       this.role = '';
       this.playerName= '';
       this.level = '';
       this.profile = 'View Stats';
  }

  ngOnInit(): void {
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.password = JSON.parse(localStorage.getItem("currentUser") as string).password;
    this.getDetails()
  }

  ngOnChanges(){
      //this.getDetails()
  }

   getDetails(){
     this.member = new Member(0, '', '', '', '', '', '', '');
         this.usersService.login().subscribe(
           dataResult => {
               console.log(dataResult);
                 const id: number = dataResult.id;
                 const name: string = dataResult.name;
                 const playerName: string = dataResult.playerName;
                 const email: string = dataResult.email;
                 const level: string = dataResult.level;
           }
         )
   }

   goBack(){
    this.router.navigate(['board'])
  }

  //  logout(): void {
  //    console.log('logging out...');
  //    this.authService.logout();

  //    this.router.navigate(['/login']);

  //  }

}

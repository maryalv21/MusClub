import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Member } from 'src/app/Models/Member.model';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: string | null;

  @Input('memberProp')
  member: Member;
  @Input()
  index: number;
  @Input()
  password: string;
  @Input()
  trainerRemoved: EventEmitter<number> = new EventEmitter();
  @Input()
  memberId!:number;


  name: string;
  playerName: string;
  email: string;
  level: string;
  id: number;

  @Output()
  deleteMemberEvent: EventEmitter<number>;

  showMember: boolean = false;
  profile: string;

  constructor(private usersService: UsersService
     ) {
    this.username = '';
    this.member = new Member(0, '', 0, '', '', '', '', '');
    this.id= 0;
    this.name = '';
    this.playerName = '';
    this.email = '';
    this.level = '';
    this.password = '';
    this.index=0;
    this.deleteMemberEvent = new EventEmitter<number>();
    this.profile = 'View Stats';
  }

  ngOnInit(): void {
    // localStorage.getItem("currentUser");
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.getDetails()
  }

  ngOnChanges(){
    this.getDetails()
  }

  getDetails(){
    this.usersService.loginMember(this.password).subscribe(
      dataResult => {
        console.log(dataResult)
        console.log(dataResult[0].name)
        const id: number = dataResult[0].id;
        const name: string = dataResult[0].name;
        const playerName: string = dataResult[0].playerName;
        const email: string = dataResult[0].email;
        const level: string = dataResult[0].level;
        this.id = id;
        this.name = name;
        this.playerName = playerName;
        this.email = email;
        this.level = level;
      }
    )
  }

  deleteMember(id: number): void {
    this.usersService.deleteMember(id).subscribe(
      dataResult => {
    console.log('eliminando member...' + id);

    this.deleteMemberEvent.emit(id);
    }
    )
  }

  onClick(): void{
    if(this.showMember === false ){
      this.showMember = !this.showMember;
      this.profile = 'Hide Stats';
    } else {
      this.showMember = false;
      this.profile = 'View Stats';
    }
  }

}

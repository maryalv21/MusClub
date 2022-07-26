import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/Models/Game.model';
import { Member } from 'src/app/Models/Member.model';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})
export class NewGameComponent implements OnInit {

  @Input()
  username:String;

  @Input()
  gameId:number;

  @Input()
  password:string;
  member: Member;
  user: User
  game : Game;
  id: number;
  playerName2: string;
  playerName3: string;
  playerName4: string;
  levels: string[];
  roles: string[];
  addresses: string[];
  avatars:string[];
  registerForm: FormGroup;
  usernameInput: FormControl;
  addressInput: FormControl;
  levelInput: FormControl;
  avatarInput: FormControl;
  playerNameInput2: FormControl;
  playerNameInput3: FormControl;
  playerNameInput4: FormControl;
  dateInput: FormControl;

  constructor(
    public gameService: GameService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) {
    this.id = 0;
    this.user = new User(0, '', '',[]);
    this.member = new Member(0,'','','','','','','');
    this.game = new Game(0,'','',[], '', '', '');
    this.roles = ['MEMBER'];
    this.levels = ['beginner'];
    this.avatars = [''];
    this.addresses = [''];
    this.username = '';
    this.password = '';
    this.gameId = 0;
    this.playerName2 = '';
    this.playerName3 = '';
    this.playerName4 = '';
    this.usernameInput = new FormControl('', [Validators.required]);
    this.dateInput = new FormControl('', [Validators.required]);
    this.playerNameInput2 = new FormControl('', [Validators.required]);
    this.playerNameInput3 = new FormControl('', [Validators.required]);
    this.playerNameInput4 = new FormControl('', [Validators.required]);
    this.avatarInput = new FormControl('', Validators.required);
    this.levelInput = new FormControl('', Validators.required);
    this.addressInput = new FormControl('', Validators.required);


    this.registerForm = new FormGroup({
      date: this.dateInput,
      playerName2: this.playerNameInput2,
      playerName3: this.playerNameInput3,
      playerName4: this.playerNameInput4,
      address: this.addressInput,
      avatar: this.avatarInput,
      level: this.levelInput,
      username: this.usernameInput
    },);
  }

  ngOnInit(): void {
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.password = JSON.parse(localStorage.getItem("currentUser") as string).password;

  }

  submitGame(): void {
    console.log('Game submitted');
    const game: Game = this.registerForm.value;
    this.gameService.createGame(game).subscribe( data => {
      console.log(game);
      alert('game created');
      const date = this.dateInput;
      const address = this.addressInput;
      const avatar = this.avatarInput;
      const username = JSON.parse(localStorage.getItem("currentUser") as string).username;
      console.log(username);
      const playerName2 = this.playerNameInput2;
      const playerName3 = this.playerNameInput3;
      const playerName4 = this.playerNameInput4;
      const level = this.levelInput;
      this.router.navigate(['board']);},
      error => {
        console.log(error);
        alert('error creating game')
    });
  }
}


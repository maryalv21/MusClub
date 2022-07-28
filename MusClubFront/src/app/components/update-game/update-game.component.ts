import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/Models/Game.model';
import { Member } from 'src/app/Models/Member.model';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-update-game',
  templateUrl: './update-game.component.html',
  styleUrls: ['./update-game.component.css']
})
export class UpdateGameComponent implements OnInit {

  currentGame:Game;
  @Input()
  username:String;

  @Input()
  index: number;
  @Input()
  showGame: number;

  @Input()
  password:string;

  @Output()
  gameId = new EventEmitter<number>();

  member: Member;
  user: User
  @Input()
  game : Game;

  @Output()
  deleteGameEvent: EventEmitter<number> = new EventEmitter<number>();

  @Output()
  updatedGameEvent: EventEmitter<number> = new EventEmitter<number>();


  addresses: string[];
  registerForm: FormGroup;
  usernameInput: FormControl;
  addressInput: FormControl;
  dateInput: FormControl;

  constructor(
    public gameService: GameService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) {

    this.currentGame = new Game(0,'','',[], '', '', '');
    this.user = new User(0, '', '',[]);
    this.member = new Member(0,'','','','','','','');
    this.game = new Game(0,'','',[], '', '', '');
    this.addresses = [''];
    this.username = '';
    this.password = '';
    this.index = 0;
    this.showGame = 0;
    this.usernameInput = new FormControl('', [Validators.required]);
    this.dateInput = new FormControl('', [Validators.required]);
    this.addressInput = new FormControl('', Validators.required);


    this.registerForm = new FormGroup({
      date: this.dateInput,
      address: this.addressInput,
      username: this.usernameInput
    },);
  }

  ngOnInit(): void {
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.password = JSON.parse(localStorage.getItem("currentUser") as string).password;
     const gameId: number = this.activatedRoute.snapshot.params['id'];
     this.gameService.findById(gameId).subscribe(
      (game)=>{
        console.log(game);
       this.currentGame = game;
       const username = this.username
      }
    );
  }

  updatedGame(): void {
    const gameId: number = this.activatedRoute.snapshot.params['id'];
    console.log('Game submitted' + this.currentGame.id + '  ' + this.gameId);
    console.log('Game data' + this.currentGame.date + '  ' + this.currentGame.address);
    const game: Game = this.registerForm.value;
    this.gameService.updateGame(this.currentGame.id, game).subscribe(
      response => {
      console.log(response);
      const date = this.dateInput;
      const address = this.addressInput;
      const username = JSON.parse(localStorage.getItem("currentUser") as string).username;
      console.log(username);
      this.router.navigate(['board']);},
      error => {
        console.log(error);
        alert('error updating game')
    });

  }



}

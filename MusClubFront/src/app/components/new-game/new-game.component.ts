import { Component, OnInit } from '@angular/core';
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

  member: Member;
  user: User
  game : Game;
  levels: string[];
  roles: string[];
  addresses: string[];
  avatars:string[];
  registerForm: FormGroup;
  addressInput: FormControl;
  levelInput: FormControl;
  avatarInput: FormControl;
  playerNameInput: FormControl;
  dateInput: FormControl;

  constructor(
    public gameService: GameService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) {
    this.user = new User(0, '', '',[]);
    this.member = new Member(0,'','','','','','','');
    this.game = new Game(0,'','',[]);
    this.roles = ['MEMBER'];
    this.levels = ['beginner'];
    this.avatars = [''];
    this.addresses = [''];
    this.dateInput = new FormControl('', [Validators.required]);
    this.playerNameInput = new FormControl('', [Validators.required]);
    this.avatarInput = new FormControl('', Validators.required);
    this.levelInput = new FormControl('', Validators.required);
    this.addressInput = new FormControl('', Validators.required);


    this.registerForm = new FormGroup({
      date: this.dateInput,
      playerName: this.playerNameInput,
      address: this.addressInput,
      avatar: this.avatarInput,
      level: this.levelInput
    },);
  }

  ngOnInit(): void {
  }

  submitGame(): void {
    console.log('Game submitted');
    const game: Game = this.registerForm.value;
    this.gameService.createGame(game).subscribe( data => {
      console.log(game);
      const date = this.dateInput;
      const address = this.addressInput;
      const avatar = this.avatarInput;
      const playerName = this.playerNameInput;
      const level = this.levelInput;
    });
    this.router.navigate(['board']);
  }

}


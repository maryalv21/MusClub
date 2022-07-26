import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/Models/Game.model';
import { User } from 'src/app/Models/User.model';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-board-details',
  templateUrl: './board-details.component.html',
  styleUrls: ['./board-details.component.css']
})
export class BoardDetailsComponent implements OnInit {

  currentGame:Game;

  @Input()
  game: Game;

  @Input()
  user: User;

  @Input()
  playerName2 : string;

  @Input()
  playerName3 : string;

  @Input()
  playerName4 : string;

  @Input()
  username: String;

  @Input()
  password: number;

  @Input()
  index: number;

  @Input()
  showGame: number;

  @Output()
  deleteGameEvent: EventEmitter<number> = new EventEmitter<number>();

  @Output()
  updatedGameEvent: EventEmitter<number> = new EventEmitter<number>();

  @Output()
  gameId = new EventEmitter<number>();

  constructor(
    private gameService: GameService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.playerName2 = '';
    this.playerName3 = '';
    this.playerName4 = '';
    this.currentGame = new Game(0,'','',[], '', '', '');
    this.user = new User(0,'','',[]);
    this.game = new Game(0,'','',[], '', '', '');
    this.username = '';
    this.password = 0;
    this.index = 0;
    this.showGame = 0;

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

  deleteGame(): void {
    const gameId: number = this.activatedRoute.snapshot.params['id'];
    console.log('eliminando game...' + this.currentGame.id);
    this.gameService.deleteGame(this.currentGame.id).subscribe(
      response => {
        console.log(response);
        alert('game deleted' + this.currentGame.address)
        this.router.navigate(['board'])
      }
    );
    console.log('esto es lo que envio al padre' + this.currentGame.id)
    this.deleteGameEvent.emit(this.currentGame.id);
  }

  updatedGame(): void{
    this.gameService.updateGame(this.currentGame.id, this.game).subscribe(
      response => {
        const playerName2 = this.playerName2;
        console.log(response);
        this.updatedGameEvent.emit(this.currentGame.id);
      }
    )
  }

  goBack(){
    this.router.navigate(['board'])
  }
}

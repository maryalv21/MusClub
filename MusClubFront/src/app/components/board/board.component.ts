import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/Models/Game.model';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  @Input()
  gameId:number;

  @Input()
  username: string;

  @Input()
  password:string;

  @Input()
  playerName2 : string;

  @Input()
  playerName3 : string;

  @Input()
  playerName4 : string;
  @Input()
  user: User;

  game: Game;
  gameList: Game[];
  selectedGame!:number;
  id: number;

  deleteGameEvent(gameId:number){
    this.gameId = gameId;
  }

  updateGameEvent(gameId: number){
    this.gameId = gameId;
  }

  showGame:number;
  date: string;
  address: string;
  playerName: string;
  avatar: string;
  level: string;

  constructor(
    private authService: AuthService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private gameService: GameService,
  ) {
    this.id = 1;
    this.playerName2 = '';
    this.playerName3 = '';
    this.playerName4 = '';
    this.password = '';
    this.username = '';
    this.gameId = 0;
    this.gameList = [];
    this.showGame= -1;
    this.user = new User(0, '', '', []);
    this.game = new Game(0, '', '', [], '', '', '');
    this.date = '';
    this.address = '';
    this.playerName = '';
    this.avatar = '';
    this.level = '';
    // this.gameService.findAll().subscribe((games:Game[]) =>
    // {
    //   this.gameList = games;
    // });
   }

  ngOnInit(): void {
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.password = JSON.parse(localStorage.getItem("currentUser") as string).password;
    //this.id = JSON.parse(localStorage.getItem("currentUser") as string).id;
    console.log(this.user.id);

     this.gameService.findAll().subscribe((games:Game[]) =>
     {
       this.gameList = games;
     });
  }

  findGameByUserId(id: number): void {
    this.username = JSON.parse(localStorage.getItem("currentUser") as string).username;
    this.password = JSON.parse(localStorage.getItem("currentUser") as string).password;
    this.gameService.findGameByUserId(this.id).subscribe((games:Game[]) => {
      console.log(this.id);
      this.gameList = games;
    })
  }

    deleteGame(gameId: number, i: number): void {
        this.gameService.deleteGame(gameId).subscribe(() => {

          this.gameList.splice(i,1);
        });
   }

  showDetails(index: number): void {
    this.selectedGame = index;
  }

  logout(): void {
    console.log('logging out...');
    this.authService.logout();
    this.router.navigate(['/login']);

  }

  /*updatedGame(gameId:number, game:Game): void{
    this.gameService.updateGame(this.gameId, this.game).subscribe(
      response => {
        const playerName2 = this.playerName2;
        console.log(response);
      }
    )
  }*/



}

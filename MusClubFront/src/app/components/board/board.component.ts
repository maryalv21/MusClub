import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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

  game: Game;
  gameList: Game[];
  selectedGame!:number;
  user: User;
  //deleteGameEvent: EventEmitter<number> = new EventEmitter<number>();
  deleteGameEvent(gameId:number){
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
    this.gameId = 0;
    this.gameList = [];
    this.showGame= -1;
    this.user = new User(0, '', '', []);
    this.game = new Game(0, '', '', []);
    this.date = '';
    this.address = '';
    this.playerName = '';
    this.avatar = '';
    this.level = '';
    this.gameService.findAll().subscribe((games:Game[]) =>
    {
      this.gameList = games;
    });
   }

  ngOnInit(): void {

     //const gameId: number = this.activatedRoute.snapshot.params['id'];
     console.log(this.activatedRoute.snapshot.params['id']);
    //console.log('esto es gameId' + gameId)
     this.gameService.findAll().subscribe((games:Game[]) =>
     {
       this.gameList = games;
     });
  }

    deleteGame(gameId: number, i: number): void {
      console.log(gameId)
        //const gameId: number= this.gameList[index].id;
        console.log('Entrando borrar'  + gameId);
        this.gameService.deleteGame(gameId).subscribe(() => {
          console.log('indice de la lista' + i)
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

  updateGame(gameId: number, game: Game): void{

  }

}

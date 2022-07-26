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


  game: Game;
  gameList: Game[];
  selectedGame!:number;
  user: User;

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
    this.gameList = [];
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
     console.log(this.activatedRoute.snapshot.params);
     const gameId: number = this.activatedRoute.snapshot.params['id'];
  }

  deleteGame(index: number): void {
      const gameId: number= this.gameList[index].id;
      this.gameList.splice(index, 1);
      console.log('Entrando borrar' + index);
      this.gameService.deleteGame(gameId).subscribe();
  }

  showDetails(index: number): void {
    this.selectedGame = index;
  }

  logout(): void {
    console.log('logging out...');
    this.authService.logout();
    this.router.navigate(['/login']);

  }

}

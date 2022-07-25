import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  games: Game[];
  user: User;

  date: string;
  address: string;
  playerName: string;
  avatar: string;
  level: string;

  constructor(
    private authService: AuthService,
    private router: Router,
    private gameService: GameService,
  ) {
    this.games = [];
    this.user = new User(0, '', '', []);
    this.game = new Game(0, '', '', []);
    this.date = '';
    this.address = '';
    this.playerName = '';
    this.avatar = '';
    this.level = '';

   }

  ngOnInit(): void {

    this.gameService.findGame(this.game).subscribe(
      (data) => {
        console.log(data);
        this.games = data;
    }
    );
  }

  logout(): void {
    console.log('logging out...');
    this.authService.logout();
    this.router.navigate(['/login']);

  }

}

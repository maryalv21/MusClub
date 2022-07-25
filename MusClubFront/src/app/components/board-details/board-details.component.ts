import { Component, OnInit, Input } from '@angular/core';
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

  @Input()
  game:Game;

  @Input()
  user:User;

  constructor(
    private gameService: GameService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.user = new User(0,'','',[]);
    this.game = new Game(0,'','',[])

  }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot.params);
    const gameId: number = this.activatedRoute.snapshot.params['id'];
    console.log(gameId)

    this.gameService.findGameId(gameId).subscribe(
      (game)=>{
        console.log(game);
       this.game = game;
      }
    );
  }


}

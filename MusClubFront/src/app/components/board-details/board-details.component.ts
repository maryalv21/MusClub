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

  @Input()
  game: Game;

  @Input()
  index: number;

  @Output()
  deleteGameEvent: EventEmitter<number> = new EventEmitter<number>();

  @Input()
  user:User;




  constructor(
    private gameService: GameService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.user = new User(0,'','',[]);
    this.game = new Game(0,'','',[]);
    this.index = 0;

  }

  ngOnInit(): void {
     console.log(this.activatedRoute.snapshot.params);
     const gameId: number = this.activatedRoute.snapshot.params['id'];
     console.log(gameId)


     this.gameService.findById(gameId).subscribe(
       (game)=>{
         console.log(game);
        this.game = game;
       }
     );
  }

  deleteGame(): void {
    console.log('eliminando game...' + this.game.id);
    // const response = {
    //   index: this.index,
    //   message: 'fan removed'
    // }
  
    this.gameService.deleteGame(this.game.id).subscribe();
    this.deleteGameEvent.emit(this.game.id);

  }
}

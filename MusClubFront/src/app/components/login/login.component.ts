import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Player } from 'src/app/Models/Player.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomValidator } from 'src/app/utils/CustomValidator';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFormPlayer: FormGroup;
  nameInput: FormControl;
  playernameInput: FormControl;


  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
    this.nameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.playernameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.loginFormPlayer = new FormGroup({
      name: this.nameInput,
      playerName: this.playernameInput
    });
   }

  ngOnInit(): void {
  }

  onSubmitPlayer(): void {
    const player: Player = this.loginFormPlayer.value;
    this.userService.loginPlayer(player).subscribe( data => {
        console.log(data);
        console.log('Login successfull');
        alert('Login successfull')
    });
      this.router.navigate(['board'])
  }
}

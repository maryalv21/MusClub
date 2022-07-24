import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Player } from 'src/app/Models/Player.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Member } from 'src/app/Models/Member.model';
import { CustomValidator } from 'src/app/utils/CustomValidator';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  player: Player;
  loginFormPlayer: FormGroup;
  nameInput: FormControl;
  playernameInput: FormControl;


  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
    this.player = new Player('', '');
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
        console.log(player);
    });
      this.router.navigate(['board'])
  }

  login() {
    this.authService.login(this.loginFormPlayer.value.username, this.loginFormPlayer.value.password).subscribe(
      (user: User) => {
        console.log('Login successful');
        console.log(user);
        // Store user in local storage to keep user logged in between page refreshes
        localStorage.removeItem('currentUser');
        localStorage.setItem('currentUser', JSON.stringify(user));
        // Redirect to home page
        this.router.navigate(['/profile']);
      }
    );
  }
}

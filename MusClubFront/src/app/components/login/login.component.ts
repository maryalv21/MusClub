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
  member: Member;
  loginFormPlayer: FormGroup;
  loginFormMember: FormGroup;
  nameInput: FormControl;
  usernameInput: FormControl;
  playernameInput: FormControl;
  emailInput: FormControl;
  passwordInput: FormControl;

  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
    this.player = new Player('', '');
    this.member = new Member(0, '', 0, '', '', '', '', '');
    this.nameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.playernameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.emailInput = new FormControl('', [Validators.required, Validators.email]);
    this.passwordInput = new FormControl('', [Validators.required, CustomValidator.passwordLength(5, 10)]);
    this.usernameInput = new FormControl('', [Validators.required]);
    this.loginFormPlayer = new FormGroup({
      name: this.nameInput,
      playerName: this.playernameInput
    });
    this.loginFormMember = new FormGroup({
      username: this.usernameInput,
      email: this.emailInput,
      password: this.passwordInput
    });
   }

  ngOnInit(): void {
  }

  // onSubmitMember(): void {
  //   console.log('Form submitted');
  //   const email: string = this.loginFormMember.value;
  //   this.userService.loginMember(email).subscribe( data => {
  //     console.log(data);
  //   });
  //   this.router.navigate(['profile'])
  // }


  onSubmitPlayer(): void {
    const player: Player = this.loginFormPlayer.value;
    this.userService.loginPlayer(player).subscribe( data => {
        console.log(player);
    });
      this.router.navigate(['board'])
  }

  login() {
    this.authService.login(this.loginFormMember.value.username, this.loginFormMember.value.password).subscribe(
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

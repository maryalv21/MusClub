import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Member } from 'src/app/Models/Member.model';
import { Player } from 'src/app/Models/Player.model';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { UsersService } from 'src/app/services/users.service';
import { CustomValidator } from 'src/app/utils/CustomValidator';

@Component({
  selector: 'app-login-member',
  templateUrl: './login-member.component.html',
  styleUrls: ['./login-member.component.css']
})
export class LoginMemberComponent implements OnInit {
  member: Member;
  loginFormMember: FormGroup;
  usernameInput: FormControl;
  passwordInput: FormControl;

  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
    this.member = new Member(0, '', 0, '', '', '', '', '');
    this.usernameInput = new FormControl('', [Validators.required]);
    this.passwordInput = new FormControl('', [Validators.required, CustomValidator.passwordLength(5, 10)]);
    this.loginFormMember = new FormGroup({
      username: this.usernameInput,
      password: this.passwordInput
    });
   }

  ngOnInit(): void {
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

  goBack(){
    this.router.navigate(['/login']);
  }
}

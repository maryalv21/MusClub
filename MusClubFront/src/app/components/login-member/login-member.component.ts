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
  nameInput: FormControl;
  usernameInput: FormControl;
  playernameInput: FormControl;
  emailInput: FormControl;
  passwordInput: FormControl;

  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
    this.member = new Member(0, '', 0, '', '', '', '', '');
    this.nameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.playernameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.emailInput = new FormControl('', [Validators.required, Validators.email]);
    this.passwordInput = new FormControl('', [Validators.required, CustomValidator.passwordLength(5, 10)]);
    this.usernameInput = new FormControl('', [Validators.required]);
    this.loginFormMember = new FormGroup({
      username: this.usernameInput,
      email: this.emailInput,
      password: this.passwordInput
    });
   }

  ngOnInit(): void {
  }

  onSubmitMember(): void {
     console.log('Form submitted');
     const email: string = this.emailInput.value;
     this.userService.loginMember(email).subscribe( data => {
        console.log('Login successful');
        console.log(data);
        localStorage.removeItem('currentUser');
         localStorage.setItem('currentUser', JSON.stringify(data));

     });
     this.router.navigate(['profile'])
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

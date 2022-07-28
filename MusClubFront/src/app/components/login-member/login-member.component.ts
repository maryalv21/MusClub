import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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
  loginFormMember: FormGroup;
  usernameInput: FormControl;
  passwordInput: FormControl;

  constructor(public usersService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
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
    this.authService.login(this.loginFormMember.value.username,
      this.loginFormMember.value.password).subscribe(
      (user:User) => {
        console.log('access granted');
        console.log(user);
        alert('login success')
        localStorage.removeItem('currentUser');
        user.password = this.loginFormMember.value.password;
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigate(['/board']);
      }
    );
  }

  goBack(){
    this.router.navigate(['/login']);
  }
}

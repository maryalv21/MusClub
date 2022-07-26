import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Member } from 'src/app/Models/Member.model';
import { User } from 'src/app/Models/User.model';
import { AuthService } from 'src/app/services/auth.service';
import { UsersService } from 'src/app/services/users.service';
import { CustomValidator } from 'src/app/utils/CustomValidator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  member: Member;
  roles: string [];
  levels: string[];
  registerForm: FormGroup;
  nameInput: FormControl;
  playernameInput: FormControl;
  emailInput: FormControl;
  passwordInput: FormControl;
  passwordConfirmationInput: FormControl;
  roleInput: FormControl;
  levelInput: FormControl;
  usernameInput: FormControl;

  constructor(public userService: UsersService,
    private authService: AuthService,
    private router:Router,
    private activatedRoute:ActivatedRoute) {
    this.member = new Member(0, '', '', '', '', '', '', '');
    this.roles = ['MEMBER'];
    this.levels = ['beginner'];
    this.nameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.playernameInput = new FormControl('', [Validators.required, CustomValidator.noDigits]);
    this.emailInput = new FormControl('', [Validators.required, Validators.email]);
    this.passwordInput = new FormControl('', [Validators.required, CustomValidator.passwordLength(5, 10)]);
    this.passwordConfirmationInput = new FormControl('');
    this.roleInput = new FormControl('', Validators.required);
    this.levelInput = new FormControl('', Validators.required);
    this.usernameInput = new FormControl('', Validators.required);


    this.registerForm = new FormGroup({
      name: this.nameInput,
      playerName: this.playernameInput,
      email: this.emailInput,
      password: this.passwordInput,
      passwordConfirmation: this.passwordConfirmationInput,
      role: this.roleInput,
      level: this.levelInput,
      username: this.usernameInput
    }, CustomValidator.passwordMatch);
  }

  ngOnInit(): void {
  }

  // onSubmit(): void {
  //   console.log('Form submitted');
  //   const member: Member = this.registerForm.value;
  //   this.userService.register(member).subscribe( data => {
  //     console.log(member);
  //   });
  //   this.router.navigate(['profile'])
  // }


  register() {
    this.authService.register(this.registerForm.value.username, this.registerForm.value.password).subscribe(
      (user: User) => {
        alert('Register successful');
        console.log(user);

        // Redirect to home page
        this.router.navigate(['/profile']);
      },
      (error) => {
        alert('Register failed');
        console.log(error);
      }
    );
  }
}

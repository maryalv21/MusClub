import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardDetailsComponent } from './components/board-details/board-details.component';
import { BoardComponent } from './components/board/board.component';
import { HomeComponent } from './components/home/home.component';
import { LoginMemberComponent } from './components/login-member/login-member.component';
import { LoginComponent } from './components/login/login.component';
import { NewGameComponent } from './components/new-game/new-game.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { UpdateGameComponent } from './components/update-game/update-game.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  {
    path: '',
    canActivate: [AuthGuardService],
    component: HomeComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'board',
    component: BoardComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'new-game',
    component: NewGameComponent,
  },
  {
    path: 'login-member',
    component: LoginMemberComponent,
  },
  {
    path:'board-details/:id',
    component: BoardDetailsComponent,
  },
  {
    path: 'update-game/:id',
    component: UpdateGameComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

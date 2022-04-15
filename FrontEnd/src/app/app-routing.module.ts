import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrdinazioneComponent } from './ordinazione/ordinazione.component';
import { OrdinazioniEffettuateComponent } from './ordinazioni-effettuate/ordinazioni-effettuate.component';
import { PermessoComponent } from './permesso/permesso.component';
import { PrenotazioneComponent } from './prenotazione/prenotazione.component';
import { PrenotazioniEffettuateComponent } from './prenotazioni-effettuate/prenotazioni-effettuate.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: HomeComponent },
  { path: "ordinazione", component: OrdinazioneComponent, canActivate: [AuthGuard] },
  { path: "prenotazione", component: PrenotazioneComponent, canActivate: [AuthGuard] },
  { path: "login", component: LoginComponent },
  { path: "preEffettuate", component: PrenotazioniEffettuateComponent, canActivate: [AuthGuard] },
  { path: "ordEffettuate", component: OrdinazioniEffettuateComponent, canActivate: [AuthGuard] },
  { path: "permesso", component: PermessoComponent },
  { path: "registrazione", component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

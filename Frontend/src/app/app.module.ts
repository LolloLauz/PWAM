import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigazioneComponent } from './navigazione/navigazione.component';
import { LoginComponent } from './login/login.component';
import { PrenotazioneComponent } from './prenotazione/prenotazione.component';
import { OrdinazioneComponent } from './ordinazione/ordinazione.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { PrenotazioniEffettuateComponent } from './prenotazioni-effettuate/prenotazioni-effettuate.component';
import { OrdinazioniEffettuateComponent } from './ordinazioni-effettuate/ordinazioni-effettuate.component';
import { ClienteServiceService } from './cliente-service.service';
import { AuthGuard } from './auth.guard';
import { AuthInterceptor } from 'src/auth.interceptor';
import { PermessoComponent } from './permesso/permesso.component';
import { RegisterComponent } from './register/register.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    NavigazioneComponent,
    LoginComponent,
    PrenotazioneComponent,
    OrdinazioneComponent,
    HomeComponent,
    PrenotazioniEffettuateComponent,
    OrdinazioniEffettuateComponent,
    PermessoComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [
    ClienteServiceService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

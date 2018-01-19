import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientXsrfModule } from '@angular/common/http';
import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterComponent } from './footer/footer.component';
import { DraftInterfaceComponent } from './draft-interface/draft-interface.component';
import { DraftSummaryComponent } from './draft-summary/draft-summary.component'

import { LoginService } from './shared/login.service';
import { DeckService } from './shared/deck.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    FooterComponent,
    DraftInterfaceComponent,
    DraftSummaryComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', component: HomePageComponent },
      { path: 'draft', component: DraftInterfaceComponent },
      { path: 'summary', component: DraftSummaryComponent }
    ]),
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'mtg-access-token',
      headerName: 'Authentication',
    })
  ],
  providers: [LoginService, DeckService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterComponent } from './footer/footer.component';
import { DraftInterfaceComponent } from './draft-interface/draft-interface.component';
import { DraftSummaryComponent } from './draft-summary/draft-summary.component';


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
    RouterModule.forRoot([
      {path:'', component:HomePageComponent},
      {path:'draft', component:DraftInterfaceComponent},
      {path:'summary', component:DraftSummaryComponent}
    ]),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

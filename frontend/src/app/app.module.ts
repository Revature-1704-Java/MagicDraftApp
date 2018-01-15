import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HomePageComponent} from './home-page/home-page.component';
import {FooterComponent} from './footer/footer.component';
import {DraftInterfaceComponent} from './draft-interface/draft-interface.component';
import {DraftSummaryComponent} from './draft-summary/draft-summary.component';
import {ModalComponent} from './modal/modal.component';
import {CardComponent} from './card/card.component';
import {CardDetailComponent} from './card-detail/card-detail.component';
import {ApiAccessService} from './shared/api-access.service';
import {SearchComponent} from './search/search.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    FooterComponent,
    DraftInterfaceComponent,
    DraftSummaryComponent,
    CardComponent,
    CardDetailComponent,
    SearchComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {path: '', component: HomePageComponent},
      {path: 'draft', component: DraftInterfaceComponent},
      {path: 'summary', component: DraftSummaryComponent},
      {path: 'cards/:cardId', component: CardDetailComponent}

    ])
  ],
  providers: [ApiAccessService],
  bootstrap: [AppComponent]
})
export class AppModule {}

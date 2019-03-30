import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {AgmCoreModule} from '@agm/core';
import {AngularMaterialModule} from './AngularMaterialModule';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ReactiveFormsModule} from '@angular/forms';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime';
import {HttpClientModule} from '@angular/common/http';
import {DeviceService} from './core/service/device.service';
import {LocationService} from './core/service/location.service';
import {DatePipe} from '@angular/common';

const config = {
  apiKey: 'AIzaSyC1w1ay-X3fWHeLPnLQ98YFdplPzNQ7qkY',
};


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgmCoreModule.forRoot(config),
    AngularMaterialModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    HttpClientModule
  ],
  providers: [
    DeviceService,
    LocationService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

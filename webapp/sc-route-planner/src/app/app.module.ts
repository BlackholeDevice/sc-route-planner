import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouteCalculatorComponent} from './route-calculator/route-calculator.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {HttpClientModule} from "@angular/common/http";
import {RcAsyncField} from './route-calculator/rc-async-field/rc-async-field.component';
import {RcStaticFieldComponent} from './route-calculator/rc-static-field/rc-static-field.component';
import {RcCalculatorCardComponent} from './route-calculator/rc-calculator-card/rc-calculator-card.component';
import {RcCoordinateCardComponent} from './route-calculator/rc-coordinate-card/rc-coordinate-card.component';
import {RcResultCardComponent} from './route-calculator/rc-result-card/rc-result-card.component';
import {RcTabbedContainerComponent} from './route-calculator/rc-tabbed-container/rc-tabbed-container.component';
import {MatTabsModule} from "@angular/material/tabs";

@NgModule({
  declarations: [
    AppComponent,
    RouteCalculatorComponent,
    RcAsyncField,
    RcStaticFieldComponent,
    RcCalculatorCardComponent,
    RcCoordinateCardComponent,
    RcResultCardComponent,
    RcTabbedContainerComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatCardModule,
        MatTableModule,
        MatGridListModule,
        MatAutocompleteModule,
        MatInputModule,
        ReactiveFormsModule,
        MatButtonModule,
        HttpClientModule,
        MatTabsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

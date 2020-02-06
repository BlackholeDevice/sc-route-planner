import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RouteCalculatorComponent} from "./route-calculator/route-calculator.component";


const routes: Routes = [
  {path: '', redirectTo: '/locations', pathMatch: 'full'},
  {path: ':tab', component: RouteCalculatorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

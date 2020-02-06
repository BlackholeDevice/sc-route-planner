import {Component, OnInit} from '@angular/core';
import {EntityService} from "./entity.service";
import {Observable} from "rxjs";
import {FormControl} from "@angular/forms";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-route-calculator',
  templateUrl: './route-calculator.component.html',
  styleUrls: ['./route-calculator.component.scss']
})
export class RouteCalculatorComponent implements OnInit {
  ngOnInit(): void {
  }

  onCalculate($event: any) {

  }
}

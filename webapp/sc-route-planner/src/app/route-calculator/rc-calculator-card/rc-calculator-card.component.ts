import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl} from "@angular/forms";
import {EntityService} from "../entity.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'rc-calculator-card',
  templateUrl: './rc-calculator-card.component.html',
  styleUrls: ['./rc-calculator-card.component.scss']
})
export class RcCalculatorCardComponent implements OnInit {
  public systems: Observable<System[]>;
  public planets: Observable<Planet[]>;
  public locations: Observable<NavLocation[]>;
  @Output() calculate = new EventEmitter();

  constructor(private entityService: EntityService) { }

  ngOnInit() {
    this.systems = this.entityService.getSystems();
  }

  public onSystemChanged(value: any): void {
    this.planets = this.entityService.getPlanets(value);
  }

  public onPlanetChanged(value: any): void {
    this.locations = this.entityService.getLocations(value);
  }

  public onCalculate(): void {
    this.calculate.emit();
  }
}

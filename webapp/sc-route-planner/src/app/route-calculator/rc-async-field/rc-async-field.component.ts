import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'rc-async-field',
  templateUrl: './rc-async-field.component.html',
  styleUrls: ['./rc-async-field.component.scss']
})
export class RcAsyncField implements OnInit {
  public control: FormControl;
  @Input() public items: Observable<any>;
  @Input() public name: string;
  @Input() public color: ThemePalette = 'accent';
  @Output() public changed = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
    this.control = new FormControl();
    this.control.valueChanges.subscribe((value) => {
      if(value.id) {
        this.changed.emit(value);
      } else {
        this.changed.emit(undefined);
      }
    })
  }

  public getName(entity: any): string {
    return entity && entity.name || ''
  }
}

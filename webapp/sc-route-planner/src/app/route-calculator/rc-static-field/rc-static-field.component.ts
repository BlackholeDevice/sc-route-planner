import {Component, Input, OnInit} from '@angular/core';
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'rc-static-field',
  templateUrl: './rc-static-field.component.html',
  styleUrls: ['./rc-static-field.component.scss']
})
export class RcStaticFieldComponent implements OnInit {
  @Input() public name: string;
  @Input() public type: string = 'text';
  @Input() public value: string = '';
  @Input() public disabled: boolean;
  @Input() public color: ThemePalette = 'accent';

  constructor() { }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {FormControl} from "@angular/forms";

const TABS = ['locations', 'coordinates', 'share'];
const NAV_LINKS = {};
TABS.forEach((tab, index) => {
  NAV_LINKS[tab] = index;
});

@Component({
  selector: 'rc-tabbed-container',
  templateUrl: './rc-tabbed-container.component.html',
  styleUrls: ['./rc-tabbed-container.component.scss']
})
export class RcTabbedContainerComponent implements OnInit {
  public navLink = new FormControl();

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe((route) => {
      this.navLink.setValue(NAV_LINKS[route.get('tab')]);
    });

    this.navLink.valueChanges.subscribe(async (value) => {
      await this.router.navigate([TABS[value]]);
    });
  }

}

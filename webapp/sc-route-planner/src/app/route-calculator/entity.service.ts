import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map, tap} from "rxjs/operators";
import {environment} from "../../environments/environment";

const TTL_MINUTES = 5;

class CacheItem {
  response: any;
  written: Date;
}

@Injectable({
  providedIn: 'root'
})
export class EntityService {
  private CACHE = new Map<string, CacheItem>();

  constructor(private http: HttpClient) { }

  public getSystems(): Observable<System[]> {
    let url = `${environment.api}/systems`;
    let cache = this.checkCache(url);
    if(cache) {
      return of(cache);
    } else {
      return this.http.get<System[]>(url).pipe(tap(this.updateCache(url)));
    }
  }

  public getPlanets(system?: System): Observable<Planet[]> {
    if(system) {
      let url = `${environment.api}/systems/${system.id}/bodies`;
      let cache = this.checkCache(url);
      if (cache) {
        return of(cache);
      } else {
        return this.http.get<Planet[]>(url).pipe(
          map((planets: Planet[]) => {
            let length = planets.length;
            for (let i = 0; i < length; i++) {
              if (planets[i].type === 'STAR') {
                planets.splice(i, 1);
                i--;
              }
              length = planets.length;
            }
            return planets;
          }),
          tap(this.updateCache(url))
        );
      }
    } else {
      return of([]);
    }
  }

  public getLocations(planet: Planet): Observable<NavLocation[]> {
    if(planet) {
      let url = `${environment.api}/bodies/${planet.id}/locations`;
      let cache = this.checkCache(url);
      if(cache) {
        return of(cache);
      } else {
        return this.http.get<NavLocation[]>(url).pipe(tap(this.updateCache(url)))
      }
    } else {
      return of([]);
    }
  }

  private updateCache(url: string): (response: any) => void {
    return (response) => {
      this.CACHE.set(url, {
        response: response,
        written: new Date()
      });
    }
  }

  private checkCache(key: string): any {
    let item = this.CACHE.get(key);
    if (item) {
      let diff = (new Date()).getTime() - item.written.getTime();
      if (diff >= TTL_MINUTES * 1000 * 60) {
        return undefined;
      } else {
        return item.response;
      }
    }
  }
}

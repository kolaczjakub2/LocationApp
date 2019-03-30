import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Location} from '../model/Location.model';

@Injectable()
export class LocationService {
  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getLocation(params): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.url}/locations`, {params: params});
  }
}

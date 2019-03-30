import {HttpClient} from '@angular/common/http';
import {Device} from '../model/Devices.model';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable()
export class DeviceService {
  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.url}/device/all`);
  }
}

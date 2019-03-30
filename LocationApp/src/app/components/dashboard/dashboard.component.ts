import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Observable, Subscription} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {Location} from '../../core/model/Location.model';
import {Device} from '../../core/model/Devices.model';
import {DeviceService} from '../../core/service/device.service';
import {HttpParams} from '@angular/common/http';
import {LocationService} from '../../core/service/location.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  lat: number = 51.678418;
  lng: number = 7.809007;
  locations: Location[] = [];
  devices: Device[] = [];
  filteredDevices: Observable<Device[]>;
  form: FormGroup;

  private readonly subscription = new Subscription();

  constructor(private fb: FormBuilder,
              private deviceService: DeviceService,
              private locationService: LocationService,
              private datePipe: DatePipe) {

  }

  ngOnInit() {
    this.form = this.initForm();
    this.subscription.add(
      this.deviceService.getAll().subscribe(response => {
        this.devices = response;
        this.getFilteredDevice();
      })
    );
  }

  private getFilteredDevice() {
    this.filteredDevices = this.form.get('device').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  displayDevice(device?: Device): string | undefined {
    return device ? device.login : undefined;
  }

  onClickButton() {
    const params = new HttpParams()
      .set('device', this.form.get('device').value.id)
      .set('dateFrom', this.datePipe.transform(this.form.get('dates').value[0], 'yyyy-MM-dd HH:mm'))
      .set('dateTo', this.datePipe.transform(this.form.get('dates').value[1], 'yyyy-MM-dd HH:mm'));

    this.subscription.add(
      this.locationService.getLocation(params).subscribe(response => {
          this.locations = response;
        }
      )
    );
  }

  private _filter(value): Device[] {
    const filterValue = value.login ? value.login.toLowerCase() : value.toLowerCase();

    return this.devices.filter(device => device.login.toLowerCase().includes(filterValue));
  }

  private initForm(): FormGroup {
    return this.fb.group({
      device: [null],
      dates: [null]
    });
  }
}

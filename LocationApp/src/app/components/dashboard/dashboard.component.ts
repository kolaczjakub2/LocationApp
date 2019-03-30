import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {Location} from "../../core/model/Location.model";
import {Device} from "../../core/model/Devices.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  title: string = 'My first AGM project';
  lat: number = 51.678418;
  lng: number = 7.809007;
  locations: Location[] = [];
  devices: Device[] = [{
    id: '1',
    login: 'Sylwunia'
  },{
    id: '2',
    login: 'Kubuniu'
  }];
  filteredDevices: Observable<Device[]>;
  form: FormGroup;


  constructor(private fb: FormBuilder) {

  }

  ngOnInit() {
    this.form = this.initForm();
    let pom: Location = new Location();
    pom.lat = 51.678418;
    pom.lng = 7.809007;

    let pom1: Location = new Location();
    pom1.lat = 52.678418;
    pom1.lng = 8.809007;
    this.locations.push(pom, pom1);

    this.filteredDevices = this.form.get('device').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  displayDevice(device?: Device): string | undefined {
    return device ? device.login : undefined;
  }
  onClickButton(){
    console.log(this.form.get('dates').value);
  }

  private _filter(value): Device[] {
    const filterValue = value.login ? value.login.toLowerCase() : value.toLowerCase();

    return this.devices.filter(device => device.login.toLowerCase().includes(filterValue));
  }

  private initForm(): FormGroup {
    return this.fb.group({
      device: [null],
      dates: [null]
    })
  }
}

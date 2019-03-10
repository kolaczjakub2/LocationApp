import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";

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
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions: Observable<string[]>;
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

    this.filteredOptions = this.form.get('user').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  private initForm(): FormGroup {
    return this.fb.group({
      user: [null]
    })
  }
}

class Location {
  lat: number;
  lng: number;
}

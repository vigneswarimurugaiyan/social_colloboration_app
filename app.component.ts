import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private httpService: HttpClient)
  {
  }
   title = 'JSON to table format';
arrdata;

  ngOnInit () {
    this.httpService.get('./assets/input.json').subscribe(
      data => {
        console.log(data[0].label);
        this.arrdata = data;
           // FILL THE ARRAY WITH DATA.
        console.log(this.arrdata[1].label);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  }
}


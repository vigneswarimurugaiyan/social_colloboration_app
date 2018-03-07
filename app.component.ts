import { Component,OnInit, } from '@angular/core';
import { Router } from '@angular/router';

import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormControl, Validators } from '@angular/forms';




@Component({
  selector: 'app-root',
 
 templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private httpService: HttpClient) {
  }
   title = 'JSON to table format';
arrdata:{};
  objectProps;
  str:string;
   myform: FormGroup;

  ngOnInit () {
    this.httpService.get('./assets/new.json').subscribe(
      data => {
      //  console.log(1);
        //console.log(data[0].value);
      //  console.log(data);
        this.arrdata = data;
           // FILL THE ARRAY WITH DATA.
        // console.log(2);
        //console.log(this.arrdata[0].value);
        //console.log(this.arrdata);
         this.objectProps =
      Object.keys(this.arrdata)
        .map(prop => {
          //console.log();
          //console.log(this.arrdata[prop]);
          return Object.assign({}, { key: prop} , this.arrdata[prop]);
        });
        console.log('hello, before objectprops');
   
    // console.log(this.objectProps[1]);
              const formGroup = {};
    for(let prop1 of Object.keys(this.arrdata)) {
       console.log(this.arrdata[prop1].key1);
      
      formGroup[prop1] = new FormControl(this.arrdata[prop1].key1 ||'');
     
    }

    this.myform = new FormGroup(formGroup);

      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  
  
  
  }




 

  onSubmit(myform) {
    console.log(this.myform);
  }

  }





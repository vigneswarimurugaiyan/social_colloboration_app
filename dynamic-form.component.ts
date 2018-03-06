import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html'

})
export class DynamicFormComponent implements OnInit {
  @Input() dataObject;
  
  objectProps;

  constructor() {
  }

  ngOnInit() {
    // remap the API to be suitable for iterating over it
    this.objectProps = 
      Object.keys(this.dataObject)
        .map(prop => {
          return Object.assign({}, { key: prop} , this.dataObject[prop]);
        });
    console.log(this.objectProps);

    // setup the form
   }
}

import { Component , OnInit } from '@angular/core';
import {FormBuilder , FormGroup ,Validators} from '@angular/forms';
declare var a:any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
  
})
export class AppComponent {
  
  nform: FormGroup;
  post:any;
  description:string = '';
  name:string = '';
  titleAlert:string = 'This field is required';
  constructor(private fb: FormBuilder) {
       //this.browser_name = "chrome";
       this.env_params = {};
      this.env_values = []; 
      this.nform = fb.group({
      'name' : [null, Validators.required],
      'description' : [null, Validators.compose([Validators.required, Validators.minLength(30), Validators.maxLength(500)])],
      'validate' : '',
       });
  }
  
   env_params:{};
   env_values :any[];

envChange(){
       console.log(this.env_values);
      console.log(this.env_params);
   }
  
  
  
  
  
  title = 'hello wow awesome';
  myobject ={name:'vigneswari',age:22};
  arr=['hello','one','two'];
  arrlist:any[]=[
  {"id":1,"name":"first"},{"id":2,"name":"second"}];
  buttonstatus:string="enabled";
  myfirstevent(event)
  {
    console.log(event);
  }
  ngOnInit()
  {
    this.nform.get('validate').valueChanges.subscribe(

      (validate) => {

          if (validate == '1') {
              this.nform.get('name').setValidators([Validators.required, Validators.minLength(3)]);
              this.titleAlert = 'You need to specify at least 3 characters';
          } else {
              this.nform.get('name').setValidators(Validators.required);
          }
          this.nform.get('name').updateValueAndValidity();

      });
    a();
  }
}
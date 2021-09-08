import React from 'react';
import ReactDOM from 'react-dom';
//import App from 'Form.js';

class Football extends React.Component {
  shoot(a,b) {
    alert(a+b);
  }
  render() {
    return (
      <button
        onClick={this.shoot.bind(this, "Goal","!!")}
      >Take the shot!</button>
    );
  }
}

class MyForm extends React.Component {
  constructor(props) {
    
    super(props);
    this.state = { username: '' , 
      age:null,
      errormessage : '',
      description : 'The content of a textarea goes in the value attribute',
      mycar : 'Volvo'
      };
  }
  mySubmitHandler = (event) => {
    event.preventDefault();
    let age = this.state.age;
    if(!Number(age)){
      alert("Your age must be a number");
    }else{
      alert("You are submitting " + this.state.username +"("+this.state.age+")");
    }
    
  }
  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    let err = '';
    if (nam === "age") {
      if (val!=="" && !Number(val)) {
        err = <strong>Your age must be a number</strong>
        //alert("Your age must be a number");
        //return
      }
    }
    this.setState({errormessage:err});
    this.setState({[nam]: val});
  }
  render() {
    let header = '';
    if (this.state.username) {
      header = <h1>Hello {this.state.username} {this.state.age}</h1>;
    } else {
      header = '';
    }

    return (
      <form onSubmit={this.mySubmitHandler}>
        {header}
        <p>Enter your name:</p>
        <input
          type='text'
          name='username'
          onChange={this.myChangeHandler}
        />
        <input 
          type ='text'
          name = 'age'
          onChange={this.myChangeHandler}
        /><br></br><br></br>
        <textarea value={this.state.description}/><br></br><br></br>
        <select value={this.state.mycar}>
        <option value="Ford">Ford</option>
        <option value="Volvo">Volvo</option>
        <option value="Fiat">Fiat</option>
      </select>
        <input type='submit' /><br></br>
        {this.state.errormessage}
        
      </form>
    );
  }
}

ReactDOM.render(<MyForm />, document.getElementById('root'));


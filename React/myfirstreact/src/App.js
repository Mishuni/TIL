import React from 'react';
import ReactDOM from 'react-dom';


class Header extends React.Component {
    constructor(props) {
      super(props);
      this.state = {favoritecolor: "red"};
    }
  
    componentDidMount() {
      setTimeout(() => {
        this.setState({favoritecolor: "yellow"})
      }, 2000)
    }
    // static getDerivedStateFromProps(props, state) {
    //   return {favoritecolor: props.favcol };
    // }
  
    shouldComponentUpdate() {
      return true;
    }
    changeColor = () => {
      this.setState({favoritecolor: "blue"});
    }
  
    getSnapshotBeforeUpdate(prevProps, prevState) {
      document.getElementById("div1").innerHTML =
      "Before the update, the favorite was " + prevState.favoritecolor;
    }
    componentDidUpdate() {
      document.getElementById("div2").innerHTML =
      "The updated favorite is " + this.state.favoritecolor;
    }
  
  
    render() {
      return (
        <div>
        <h1>My Favorite Color is {this.state.favoritecolor}</h1>
        <button type="button" onClick={this.changeColor}>Change color</button>
        <div id="div1"></div>
        <div id="div2"></div>
        </div>
      );
    }
  }
  

class Car extends React.Component {
    constructor(props){
        super(props);
        this.state = {brand:"Ford", model:"Mustang", color:"red"};
    }

    changeColor = () => {
        if (this.state.color=="red"){
            this.setState({color:"blue"});
        }
        else{
            this.setState({color:"red"});
        }
       
    }

  render() {
    return (
        <div>
        <h2>I am a {this.props.brand}!</h2>
        <h2>OR {this.state.brand}!</h2>
        <p>
            It is a {this.state.color} 
            {this.state.model}
        </p>
        <button 
            type="button"
            onClick={this.changeColor}>
                Change Color
        </button>
        </div>
        );
  }
}




export default Car;

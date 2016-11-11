'use strict'

import 'bootstrap/dist/css/bootstrap.css';

const React = require('react');
const ReactDOM = require('react-dom')

const root = '/api';

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render(){
        return(
            <div>Hello!</div>
        );
    }
}

ReactDOM.render(
<App />,
    document.getElementById('react')
)
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {entries: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/entries'}).done(response => {
            this.setState({entries: response.entity._embedded.entries});
        });
    }

    render() {
        return (
            <EntriesList employees={this.state.entries}/>
        )
    }
}

class EntriesList extends React.Component{
    render() {
        const entries = this.props.employees.map(entry =>
            <Entry key={entry._links.self.href} entry={entry}/>
        );
        return (
            <table>
                <tbody>
                {entries}
                </tbody>
            </table>
        )
    }
}

class Entry extends React.Component{
    render() {
        return (
            <div>
            <tr>
                <td>{this.props.entry.localDateTime}</td>

            </tr>
            <tr>
                <td>{this.props.entry.entryCode}</td>
            </tr>
            </div>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
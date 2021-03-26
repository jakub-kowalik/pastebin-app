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
            this.setState({entries: response.entity});
        });
    }

    render() {
        return (
            <EntriesList employees={this.state.entries}/>
        )
    }
}

class EntriesList extends React.Component {
    render() {
        const entries = this.props.employees.map(entry =>
            <Entry key={entry.id} entry={entry}/>
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

class Entry extends React.Component {
    render() {
        return (
            <div class="entry">
                <table>
                    <thead>
                    <tr valign="top">
                        <th>
                            {this.props.entry.localDateTime}
                        </th>
                    </tr>
                    <tr>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <hr></hr>
                            {this.props.entry.entryCode}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        )
    }
}

ReactDOM.render(
    <App/>
    ,
    document.getElementById('react')
)
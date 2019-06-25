import React, { Component } from 'react';
import api from '../services/api';
import GithubData from '../GithubData'


class DataProvider extends Component {
    constructor(props) {
        super(props);
        this.state = {
            github: "",
            name: "",
            avatar_url: "",
            company: "",
            blog: "",
            location: "",
            email: "",
            bio: "",
            created_at: ""
        }
    }

    componentDidMount() {
        this.loadProducts();
    }

    loadProducts = async () => {
        const response = await api.get('/users/jpoliveira12345');
        this.setState({
            github: response.data.html_url,
            avatar_url: response.data.avatar_url,
            name: response.data.name,
            company: response.data.company,
            blog: response.data.blog,
            location: response.data.location,
            email: response.data.email,
            bio: response.data.bio,
            created_at: response.data.created_at
        })
    }

    render() {
        return (
            <GithubData.Provider value={
                { state: this.state }
            }>
                {this.props.children}
            </GithubData.Provider>
        )
    }
}

export default DataProvider;
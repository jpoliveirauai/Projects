import React, { Component } from 'react';
import api from "../../services/api";

export default class  extends Component {

    state = {
        github: "",
        name: "",
        company: "",
        blog: "",
        location: "",
        email: "",
        bio: "",
        created_at: ""
    }
    componentDidMount() {
        this.loadProducts();
    }

    loadProducts = async () => {
        const response = await api.get('/users/jpoliveira12345');
        this.setState({
            github: response.data.html_url,
            name: response.data.name,
            company: response.data.company,
            blog: response.data.blog,
            location: response.data.location,
            email: response.data.email,
            bio: response.data.bio,
            created_at: response.data.created_at
        })
        console.log(this.state.github)
    }

    render() {
        return <h1>{this.state.name}</h1>;
    }
}
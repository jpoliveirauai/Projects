import React, { Component } from 'react';
import './Landingpage.css';
import { Grid, Cell } from 'react-mdl';

class Landingpage extends Component {
    render(){
        return (
            <div style={{width:'100%', margin: 'auto'}} >
                <Grid className="landing-grid">
                    <Cell col='12' />
                </Grid>
            </div>
        );
    }
}

export default Landingpage;
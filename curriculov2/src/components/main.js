import React from 'react';
import { Switch, Route } from 'react-router-dom'
import About from './about/About'
import Contact from './contact/Contact';
import Landingpage from './landingpage/Landingpage';
import Resume from './resume/Resume';
import Projects from './projects/Projects';

const Main = () => (
    <Switch>
        <Route exact path="/about" component={About} />
        <Route exact path="/resume" component={Resume} />
        <Route exact path="/contact" component={Contact} />
        <Route exact path="/landingpage" component={Landingpage} />
        <Route exact path="/projects" component={Projects} />
    </Switch>
)

export default Main;
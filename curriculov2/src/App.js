import React from 'react';
import './App.css';
import Main from './components/main'
import { Layout, Header, Navigation, Content } from 'react-mdl';
import GithubData from './components/GithubData'
import DataProvider from './components/dataProvider/DataProvider';


function App() {
    return (
        <div >
            {/* style={{height: '500px', position: 'relative'}} */}
            <Layout className='layout-color' style={{ background: 'url(http://www.getmdl.io/assets/demos/transparent.jpg) center / cover' }}>
                <Header className='header-color' title="Title" transparent>
                    {/* transparent title="Title" style={{color: 'white'}} */}
                    <Navigation>
                        <a href="/about    ">About</a>
                        <a href="/resume">Resume</a>
                        <a href="/contact">Contact</a>
                        <a href="/projects">Projects</a>
                    </Navigation>
                </Header>
                <Content>
                    <div className="page-content">
                        <Main />
                        <DataProvider>
                            <GithubData.Consumer>
                                {(context) => (
                                    <p>Name: {context.state.name}</p>
                                )}
                            </GithubData.Consumer>
                        </DataProvider>

                    </div>
                </Content>
            </Layout>
        </div>
    );
}

export default App;

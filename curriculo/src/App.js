import React from 'react';
import api from './services/api'
import './styles.css'
import Header from "./comonents/Header"
import Main from './pages/main'
function App() {
  return (
    <div className="App">
        <Header/>
        <Main/>
    </div>
  );
}

export default App;

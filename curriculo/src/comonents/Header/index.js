import React, { Component } from 'react';
import './styles.css';
// import state from '../../pages/main'

class Header extends Component {

    render() {
        return (
        <header id="main-header">
        <span id="logo">
            João Paulo de Oliveira
        </span>
        <aside id="Menus">
            <nav>
                <ul>
                    <li><a> About me    </a></li>
                    <li><a> Talks       </a></li>
                    <li><a> Research    </a></li>
                </ul>
            </nav>
        </aside>
        </header>
        );
    }
}

export default Header;
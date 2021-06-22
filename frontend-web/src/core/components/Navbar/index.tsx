import React from 'react';
import './styles.scss';

//Nao precisa das chaves {} pois vai retornar só html.
const Navbar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <a href = "link" className="nav-logo-text">
                <h4>DS Catalog</h4>
            </a>
        </div>
        {/*offset-2 pula duas colunas a esquerda*/}
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <a href="link" className="active">
                        HOME
                    </a>
                </li>
                <li>
                    <a href="link">
                        CATÁLOGO
                    </a>
                </li>
                <li>
                    <a href="link">
                        ADMIN
                    </a>
                </li>
            </ul>
        </div>
    </nav>
);

export default Navbar;
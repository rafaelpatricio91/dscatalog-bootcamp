import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import './styles.scss';

//Nao precisa das chaves {} pois vai retornar só html.
const Navbar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <Link to = "/" className="nav-logo-text">
                <h4>DS Catalog</h4>
            </Link>
        </div>
        {/*offset-2 pula duas colunas a esquerda*/}
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <NavLink to="/" activeClassName="active" exact>
                        HOME
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/catalog" activeClassName="active">
                        CATÁLOGO
                    </NavLink>
                </li>
                <li>
                    <NavLink to="admin" activeClassName="active">
                        ADMIN
                    </NavLink>
                </li>
            </ul>
        </div>
    </nav>
);

export default Navbar;
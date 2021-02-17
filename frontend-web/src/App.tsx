import React, { useState, useEffect } from 'react';

const App = () => {
    const [counter, setCounter] = useState(0);

    useEffect(() => {
        console.log("componente modificado");
    }, [counter]);

   return (
       <div className="container mt-5"> 
        <button className="btn btn-primary mr-5"
                onClick={() => setCounter(counter+1) }> 
            +
        </button>
        <span>
            {counter}
        </span> 
        <button className="btn btn-primary ml-5"
                onClick={() => setCounter(counter-1) }> 
            -
        </button>
        {counter > 3 && <h1>O valor é maior que 3</h1>}
       </div>
   )
}

export default App;
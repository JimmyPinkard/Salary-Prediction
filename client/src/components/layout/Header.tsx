import React, {Fragment} from 'react';

function Header({title}:{title:string}) {
  return (
  <Fragment>
    <h1>Salary Prediction</h1>
    <h2>{title}</h2>
  </Fragment>
  );
}

export default Header;

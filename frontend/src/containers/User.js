import React from "react"

const User = (props) => {
    console.log("props:", props.match.params.user)
    return (
        <div>
            Ahla {props.match.params.user}
        </div>
    );
}

export default User;
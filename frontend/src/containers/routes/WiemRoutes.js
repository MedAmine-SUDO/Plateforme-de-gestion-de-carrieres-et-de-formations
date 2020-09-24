import React from 'react'
import { Route } from "react-router-dom";
import RessourcePage from '../Pages/RessourcePage/RessourcePage'
function WiemRoutes() {
    return (
        <>
        {/* Upload Ressources */}
        <Route path={"/uploadRessource"} exact component={RessourcePage} />
            
        </>
    )
}

export default WiemRoutes

import React from 'react'
import { Route } from "react-router-dom";
import RessourcePage from '../Pages/RessourcePage/RessourcePage'
import NotificationPage from '../Pages/Notif/NotificationPage'

function WiemRoutes() {
    return (
        <>
        {/* Upload Ressources */}
        <Route path={"/uploadRessource"} exact component={RessourcePage} />
        <Route path={"/notifications"} exact component={NotificationPage} />

            
        </>
    )
}

export default WiemRoutes

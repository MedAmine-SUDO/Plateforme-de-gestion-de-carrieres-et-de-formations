import React from 'react'
import { Route } from "react-router-dom";
import TestPage from "../Pages/TestPage/TestNiveauAdmin";

function AmineRoutes() {
    return (
        <>
            <Route path="/test" exact component={TestPage} />

        </>
    )
}
export default AmineRoutes

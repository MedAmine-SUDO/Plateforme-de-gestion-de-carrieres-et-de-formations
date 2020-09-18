import React from 'react'
import { Route } from "react-router-dom";
import TestPage from "../Pages/TestPage/TestPage";

function AmineRoutes() {
    return (
        <>
            <Route path="/test" exact component={TestPage} />

        </>
    )
}
export default AmineRoutes

import React from "react";
import NavPills from "../../../components/NavPills/NavPills.js";
import TestNiveauQuestionTable from "./Sections/TestNiveauQuestionTable.js";

function TestNiveauAdmin() {

  return (
    <NavPills
      color="adminDashboard"
      tabs={[{
        tabButton: "Questions", tabContent: (
          <>
            <TestNiveauQuestionTable />
          </>
        )
      }, { tabButton: "Tests" }]}
    />
  );
}

export default TestNiveauAdmin;

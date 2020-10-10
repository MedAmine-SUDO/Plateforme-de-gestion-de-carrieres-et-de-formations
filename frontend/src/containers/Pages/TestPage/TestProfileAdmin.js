import React from "react";
import NavPills from "../../../components/NavPills/NavPills.js";
import TestProfileQuestionTable from "./Sections/TestProfileQuestionTable.js";

function TestProfileAdmin() {

  return (
    <NavPills
      color="adminDashboard"
      tabs={[{
        tabButton: "Questions", tabContent: (
          <>
            <TestProfileQuestionTable />
          </>
        )
      }, { tabButton: "Tests" }]}
    />
  );
}

export default TestProfileAdmin;

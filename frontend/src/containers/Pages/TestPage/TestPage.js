import React from "react";
import NavPills from "../../../components/NavPills/NavPills.js";
import QuestionTable from "./Sections/QuestionTable.js";

function TestPage() {

  return (
    <NavPills
      color="primary"
      tabs={[{
        tabButton: "Questions", tabContent: (
          <>
            <QuestionTable />
          </>
        )
      }, { tabButton: "Tests" }]}
    />
  );
}

export default TestPage;

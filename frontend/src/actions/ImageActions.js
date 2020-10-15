// import {  } from "../apiCall/index";

// export const imageGetData = (id) => async (dispatch) => {
//   try {
//     dispatch({ type: "IMAGE_LOADING" });
//     const res = await apiCallRessource("/"+id, "get");
//     dispatch({ type: "IMAGE_GET_DATA", payload: res.data });

//     return res;
//   } catch (err) {
//     dispatch({ type: "IMAGE_END_LOADING" });

//   }
// };
// export const ressourceGetAll = (id) => async (dispatch) => {
//   try {
//     dispatch({ type: "IMAGE_LOADING" });
//     const res = await apiCallRessource("/all", "get");
//     dispatch({ type: "IMAGE_GET_DATA", payload: res.data });
//     return res;
//   } catch (err) {
//     dispatch({ type: "IMAGE_END_LOADING" });
//   }
// };
// const config= {
 
//     'content-type': 'multipart/form-data'
  
// }
// export const ressourcePostData = (ressource) => async (dispatch) => {
//   try {
//     console.log('here')

//     dispatch({ type: "IMAGE_LOADING" });
//     const res = await apiCallRessource("/add","post",ressource,config);

//     dispatch({ type: "IMAGE_GET_DATA", payload: res.data });

//     return res;
//   } catch (err) {
//     dispatch({ type: "IMAGE_END_LOADING" });

//   }
// };
// export const ressourceUpdateData = (ressource) => async (dispatch) => {
//   try {
//     dispatch({ type: "IMAGE_LOADING" });

//   const res = await apiCallRessource("/update", "put",ressource,config);
//     dispatch({ type: "IMAGE_GET_DATA", payload: res.data });
//     return res;
//   } catch (err) {
//     dispatch({ type: "IMAGE_END_LOADING" });
//   }
// };

// export const ressourceDeleteData = (id) => async (dispatch) => {
//   try {
//     dispatch({ type: "RESSOURCE_LOADING" });
//     const res = await apiCallRessource("/"+id, "delete");
//     dispatch({ type: "RESSOURCE_GET_DATA", payload: res.data });

//     return res;
//   } catch (err) {
//     dispatch({ type: "RESSOURCE_END_LOADING" });
//   }
// };



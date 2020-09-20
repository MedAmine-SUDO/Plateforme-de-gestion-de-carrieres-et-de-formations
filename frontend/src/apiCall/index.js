import axios from 'axios';

const composeToken = (token) => token ? { Authorization: `Bearer ${token}` } : {};

const apiCallAuth = (url, method, body = {}, token = '') => axios({
  method,
  url: `http://localhost:8081/api${url}`,
  data: body,
  headers: {
    ...composeToken(token)
  }
});

const apiCallProfile = (url, method, body = {}, token = '') => axios({
  method,
  url: `http://localhost:8082/api/profile${url}`,
  data: body,
  headers: {
    ...composeToken(token)
  }
});

const apiCallFormation = (url, method, body = {}, token = '') => axios({
  method,
  url: `http://localhost:8083/api/profile${url}`,
  data: body,
  headers: {
    ...composeToken(token)
  }
});

const apiCalltestNiveau = (url, method, body = {}, token = '') => axios({
  method,
  url: `http://localhost:8089/api/testNiveau${url}`,
  data: body,
  headers: {
    ...composeToken(token)
  }
});
const apiCallRessource = (url, method, body = {}, token = '') => axios({
  method,
  url: `http://localhost:8089/api/ressource${url}`,
  data: body,
  headers: {
    ...composeToken(token)
  }
});


export {apiCallAuth , apiCallProfile, apiCallFormation, apiCalltestNiveau , apiCallRessource };

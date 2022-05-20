import axios from "../../node_modules/axios";

export const AXIOS = axios.create({
  baseURL: "http://127.0.0.1:8093/graphql",
  headers: {
    "Content-type": "application/json",
  },
});

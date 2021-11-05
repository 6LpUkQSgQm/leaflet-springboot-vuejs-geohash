import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: "http://localhost:8091/graphql",
  headers: {
    "Content-type": "application/json"
  }
})

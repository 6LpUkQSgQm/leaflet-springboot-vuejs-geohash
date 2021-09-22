import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-type": "application/json"
  }
})

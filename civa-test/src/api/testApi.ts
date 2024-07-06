import axios from "axios";
import { getEnvVariables } from "@/helper";

const {VITE_BASE_URL}  = getEnvVariables();

console.log(VITE_BASE_URL)

const testApi = axios.create({
  baseURL: VITE_BASE_URL,
});


testApi.interceptors.request.use((config) => {
  config.headers["Content-Type"] = "application/json";
  const token = localStorage.getItem("token");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

export default testApi;

import axios from "axios";
import { notification } from "ant-design-vue";
import router from "@/router";
import { useToken } from "@/store/token";

const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_URL,
  // timeout: 3000
});

service.interceptors.request.use((config) => {
  const tokenStore = useToken();
  if (tokenStore.token) {
    config.headers["Authorization"] = `Bearer ${tokenStore.token}`;
  } else {
    router.push("/login");
  }
  return config
});
service.interceptors.response.use((res) => {
  const code = res?.data.code;
  if (code === 401) {
    console.log(`output->router`, router);
    router.push("/login");
    return;
  }
  if (code === 200) {
    return res.data;
  } else {
    notification.error({
      message: "系统提示",
      description: res.data.message,
    });
  }
});
export default service;

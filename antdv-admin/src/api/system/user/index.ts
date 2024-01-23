import service from "@/api/request";

export function addUser(params: any) {
    return service.post("/user/add", params)
}

export function login(params: any){
    return service.post("/login", params)
}
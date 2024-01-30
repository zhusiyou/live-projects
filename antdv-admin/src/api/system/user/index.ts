import service from "@/api/request";

export function addUser(params: any) {
    return service.post("/user/add", params)
}

export const editUser = (user: any) => {
    return service.post('/user/edit', user)
}

export function login(params: any){
    return service.post("/login", params)
}

export const list = ()=>{
    return service.get("/user/list")
}
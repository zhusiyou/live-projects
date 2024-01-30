import service from "@/api/request";

export const addRole = (role: any) => {
    return service.post('/role/add',  role)
}

export const editRole = (role: any) => {
    return service.post('/role/edit', role)
}

export const list = ()=>{
    return service.get('/role/list')
}
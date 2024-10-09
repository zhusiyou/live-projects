import service from "@/api/request";

export const addPermission = (permission: Permission) => {
    return service.post('/permission/add',  permission)
}

export const editPermission = (permission: Permission) => {
    return service.post('/permission/edit', permission)
}

export const list = ()=>{
    return service.get('/permission/list')
}

export const roles = (permission: Permission) => {
    return service.get(`/role-permission/roles/${permission.permissionId}`)
}

export const saveRoles = (roles: string[], permissionId?: string) => {
    return service.post(`/role-permission/roles/${permissionId}`, roles)
}
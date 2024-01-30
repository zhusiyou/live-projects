import service from '@/api/request'

export const getUserRoles = (userId: string) => {
    return service.get(`/user-role/user/${userId}`)
}

export const edit = (userId: string, roles: string[]) => {
    return service.post(`/user-role/user/${userId}`, roles)
}
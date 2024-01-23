import { defineStore } from 'pinia'
import { computed, ref } from 'vue'

export const useToken = defineStore('access-token', ()=>{
    const tokenRef = ref<string>('')
    const token = computed(()=> tokenRef.value)
    const store = (t:string) => tokenRef.value = t

    return {tokenRef, token, store}
})
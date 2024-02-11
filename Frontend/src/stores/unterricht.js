import { defineStore, acceptHMRUpdate } from 'pinia'

export const useUnterrichtStore = defineStore('unterricht', {
 state: () => ({
    unterricht: [],
 }),
 getters: {},
 actions: {
    setUnterricht(unterricht) {
       this.unterricht = unterricht
       console.log(this.unterricht)
    },
    reset() {
       this.setUnterricht([])
    },
 },
})

if (import.meta.hot) {
 import.meta.hot.accept(acceptHMRUpdate(useUnterrichtStore, import.meta.hot))
}

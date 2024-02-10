import { defineStore, acceptHMRUpdate } from 'pinia'

export const useStundenStore = defineStore('stunden', {
 state: () => ({
   stunden: [], 
   date: "",
 }),
 getters: {},
 actions: {
    setStunden(stunden) {
        this.stunden = stunden
    },
    setDate(date) {
        this.date = date
    },
    reset() {
        this.setStunden([])
    },
 },
})

if (import.meta.hot) {
 import.meta.hot.accept(acceptHMRUpdate(useStundenStore, import.meta.hot))
}

import { defineStore, acceptHMRUpdate } from 'pinia'

export const useStundenStore = defineStore('stunden', {
 state: () => ({
   stunden: [], 
   date: "",
   aktuelleStunde: {},
 }),
 getters: {},
 actions: {
    setStunden(stunden) {
        this.stunden = stunden
    },
    setDate(date) {
        this.date = date
    },
    setAktuelleStunde(aktuelleStunde) {
        this.aktuelleStunde = aktuelleStunde;
    },
    reset() {
        this.setStunden([])
        this.date = ""
        this.aktuelleStunde = {}
    },
 },
})

if (import.meta.hot) {
 import.meta.hot.accept(acceptHMRUpdate(useStundenStore, import.meta.hot))
}

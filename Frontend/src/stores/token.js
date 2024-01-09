import { defineStore } from 'pinia';

export const useTokenStore = defineStore({
    id: 'auth',
    state: () => ({
        token: null,
    }),
    actions: {
        setToken(token) {
            this.token = token;
        },
    },
});
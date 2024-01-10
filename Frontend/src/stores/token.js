import { defineStore } from 'pinia';

export const useTokenStore = defineStore({
    id: 'token',
    state: () => ({
        token: null,
    }),
    actions: {
        setToken(token) {
            this.token = token;
        },
    },
});
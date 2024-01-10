import { defineStore } from "pinia";

export const useAccountIdStore = defineStore({
    id: "account",
    state: () => ({
        accountId: null,
    }),
    actions: {
        setAccountId(accountId) {
            this.accountId = accountId;
        },
    },
});

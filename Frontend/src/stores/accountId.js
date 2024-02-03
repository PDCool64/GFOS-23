import { defineStore } from "pinia";

export const useAccountIdStore = defineStore("id", {
    state: () => ({
        accountId: null,
    }),
    actions: {
        setAccountId(accountId) {
            this.accountId = accountId;
        },
    },
});

import { defineStore, acceptHMRUpdate } from "pinia";

export const useUserStore = defineStore("user", {
	state: () => ({
		user: {
			id: 0,
			name: "",
			email: "",
			isAdmin: false,
		},
		token: "",
	}),
	getters: {},
	actions: {
		setToken(token) {
			this.token = token;
		},
		setUserId(id) {
			this.user.id = id;
		},
		setIsAdmin(isAdmin) {
			this.user.isAdmin = isAdmin;
		},
		reset() {
            this.setToken("");
            this.setUserId(0);
		},
	},
	persist: {
		storage: sessionStorage,
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot));
}

import { defineStore, acceptHMRUpdate } from "pinia";

export const useUserStore = defineStore("user", {
	state: () => ({
		user: {
			id: 0,
			name: "",
			vorname: "",
			email: "",
			isAdmin: false,
			isLeiter: false,
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
		setData(data){
			this.token = data.token;
			this.user.email = data.account.email
			this.user.id = data.account.id;
			this.user.name = data.account.name;
			this.user.vorname = data.account.vorname
			this.user.isAdmin = data.account.isadmin;
		},
		setIsLeiter(isLeiter) {
			this.user.isLeiter = isLeiter;
		},
		setStammdaten(data){
			this.user.email = data.email
			this.user.id = data.id;
			this.user.name = data.name;
			this.user.vorname = data.vorname
		}
	},
	persist: {
		storage: sessionStorage,
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot));
}

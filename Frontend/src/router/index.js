import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "home",
			component: HomeView,
		},
		{
			path: "/about",
			name: "about",
			// route level code-splitting
			// this generates a separate chunk (About.[hash].js) for this route
			// which is lazy-loaded when the route is visited.
			component: () => import("../views/AboutView.vue"),
		},
		{
			path: "/login",
			name: "login",
			component: () => import("../views/LoginView.vue"),
		},
		{
			path: "/register",
			name: "register",
			component: () => import("../views/RegistrationView.vue"),
		},
		{
			path: "/profile",
			name: "profile",
			component: () => import("../views/StammdatenView.vue"),
		},
		{
			path: "/password",
			name: "password",
			component: () => import("../views/PasswortView.vue"),
		},
		{
			path: "/stundenplan",
			name: "stundenplan",
			component: () => import("../views/Stundenplan.vue"),
		},
		{
			path: "/stundenplan/:day/",
			name: "dynamic-stundenplan",
			component: () => import("../views/DynamischerStundenplan.vue"),
		},
		{
			path: "/kurs/create",
			name: "create-kurs",
			component: () => import("../views/CreateKursView.vue"),
		},
		{
			path: "/kurs/choose",
			name: "choose-kurs",
			component: () => import("../views/ChooseKursView.vue"),
		},
		{
			path: "/kurs/verwalten/:kurs",
			name: "kurs-verwalten",
			component: () => import("../views/KursVerwaltungView.vue"),
		},
		{
			path: "/kurs/:kurs/members",
			name: "kurs-members",
			component: () => import("../views/KursMembersView.vue"),
		},
		{
			path: "/unterricht/:day/:time",
			name: "unterricht",
			component: () => import("../views/UnterrichtView.vue"),
		},
		{
			path: "/unterricht/create/:kurs",
			name: "create-unterricht",
			component: () => import("../views/CreateUnterrichtView.vue"),
		},
		{
			path: "/unterricht/delete/:unterricht",
			name: "delete-unterricht",
			component: () => import("../views/DeleteUnterrichtView.vue"),
		},
		{
			path: "/stunde/:day/:time",
			name: "stunde",
			component: () => import("../views/StundeView.vue"),
		},
	],
});

export default router;

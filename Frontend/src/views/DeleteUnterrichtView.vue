<template>
	<div class="wrapper">
		<div class="grid">
			<div
				v-for="u in unterricht"
				:key="u.id"
				@click="delete_(u.id)"
				class="kurs-item"
			>
				<h2>{{ tage[u.tag] }}</h2>
				<p v-if="u.beginstunde != u.endstunde">{{ u.beginstunde + 1 }}. bis {{ u.endstunde + 1 }}. Stunde</p>
				<p v-else>{{ u.beginstunde + 1 }}. Stunde</p>
			</div>
		</div>
	</div>	
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";
import { getAllUnterrichtByKurs, deleteUnterricht } from "@/requests/unterricht";
import router from "@/router";

const userData = useUserStore();

const route = useRoute();
const unterrichtId = route.params.unterricht;

const unterricht = ref([]);

const tage = [
	"Montag",
	"Dienstag",
	"Mittwoch",
	"Donnerstag",
	"Freitag",
	"Samstag",
	"Sonntag",
];

async function setup() {
	console.log(unterrichtId);
	unterricht.value = await getAllUnterrichtByKurs(unterrichtId);
}

async function reload() {
	unterricht.value = await getAllUnterrichtByKurs(unterrichtId);
}

const delete_ = async (id) => {
	await deleteUnterricht(id);
	reload();
};

onMounted(() => {
	setup();
});

</script>

<style scoped>
@import "../assets/shared_styles/chooser.css";

.wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100%;
	width: 100%;
}


button {
    margin-top: 20px;
}
</style>

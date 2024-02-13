<template>
	<div class="wrapper">
		<div
			@click="createUnterricht"
			class="kurs-item"
		>
			<h2>Unterricht erstellen</h2>
		</div>
        <div
            @click="deleteUnterricht"
            class="kurs-item"
        >   
            <h2>Unterricht löschen</h2>
        </div>    
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { useRoute } from "vue-router";

const route = useRoute();
const kursId = route.params.kurs;


const userData = useUserStore();


const kurse = ref([]);


function deleteUnterricht() {
    router.push("/unterricht/delete/" + kursId);
}

function createUnterricht() {
    router.push("/unterricht/create/" + kursId);
}
    
</script>

<style scoped>
@import "../assets/shared_styles/chooser.css";
</style>

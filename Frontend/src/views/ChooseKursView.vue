<template>
	<div class="wrapper">
		<div
			v-for="kurs in kurse"
			:key="kurs.id"
			@click="createUnterricht(kurs.id)"
			class="kurs-item"
		>
			<h2>{{ kurs.fach }}({{ kurs.art }}{{ kurs.nummer }})</h2>
			<p>Stufe: {{ kurs.stufe }}</p>
		</div>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import router from "@/router";


const userData = useUserStore();


const kurse = ref([]);

function createUnterricht(kursId) {
    router.push("../unterricht/create/" + kursId);
}

async function getKurse() {
    const response = await fetch("http://localhost:8080/Backend/kurs/leiter/", {
        method: "GET",
        headers: {
            Authorization: userData.token,
        },
    });

    if (response.ok) {
        const data = await response.json();
        kurse.value = data;
    }
    else {
        console.log("Error fetching data");
    }
}
getKurse();
    
</script>

<style scoped>
.kurs-item {
    border: 3px solid var(--third-color);
    border-radius: 10px;
	padding: 10px;
	margin-bottom: 10px;
	cursor: pointer;
}
.kurs-item:hover {
    background-color: var(--fourth-color);
    transition: ease-in-out 0.3s;
}

.wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    flex-grow: 1;
}
</style>

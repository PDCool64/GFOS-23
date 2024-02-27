<template>
	<div class="wrapper">
		<h1>{{ code }}</h1>
        <RouterLink :to="`/stunde/anwesenheit/${id}`">Zurück</RouterLink>
	</div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { getCheckInCode } from "@/requests/stunde";
import { RouterLink } from "vue-router";

const route = useRoute();

const id = route.params.id;

const code = ref(id);

onMounted(() => {
	getCheckInCode(id).then((res) => {
		code.value = res;
	});
});
</script>

<style scoped>
a{ 
    background-color: var(--fourth-color);
    padding: 10px 20px;
    color: var(--color-text);
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: calc(var(--text-size) * 1.2);
}
.wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100%;
	width: 100%;
}

h1 {
	color: var(--color-text);
	font-size: calc(var(--text-size) * 8);
	text-align: center;
	letter-spacing: 1.6cm;
	@media (max-width: 768px) {
		letter-spacing: 1cm;
	}
    @media (max-width: 550px) {
        letter-spacing: 0.5cm;
    }
    @media (max-width: 450px){
        letter-spacing: 0cm; 
    }
}
</style>

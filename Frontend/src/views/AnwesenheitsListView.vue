<template>
	<div class="outer-wrapper">
		<div class="button-wrapper">
			<div class="wrapper">
				<table class="custom-table">
					<tr class="table-header">
						<th>Name</th>
						<th>Vorname</th>
						<th>Anwesend</th>
						<th>Note</th>
					</tr>
					<tr v-for="(data, index) in sampleData" :key="index">
						<td>{{ data.account.name }}</td>
						<td>{{ data.account.vorname }}</td>
						<td @click="changeAnwesend(index)" class="center-image">
							<img
								v-if="data.anwesend"
								class="anwesend-image clickable"
								src="@/assets/pictures/haekchen.png"
								alt="Anwesend"
							/>
							<img
								v-else
								class="anwesend-image clickable"
								src="@/assets/pictures/kreuz.png"
								alt="Nicht Anwesend"
							/>
						</td>
						<td>
							<input
								type="text"
								placeholder="Note"
								v-model="data.note"
								:disabled="!data.anwesend"
							/>
						</td>
					</tr>
				</table>
			</div>
			<button @click="updateTeilnahmen(toJson, stundenId)">Save</button>
		</div>
	</div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import data from "@/assets/test_data/anwesenheit.json";
import { useRoute } from "vue-router";
import { updateTeilnahmen, getTeilnahmen } from "@/requests/stunde";


const route = useRoute();

const stundenId = route.params.id;
console.log(stundenId);

const sampleData = ref([]);
const toJson = computed(() => {
    let res = sampleData.value;
    for (let i = 0; i < res.length; i++) {
        if (res[i].note === "" || res[i].note === "-") {
            res[i].note = null;
        }
    }
    return JSON.stringify(res);
});


const setup = async () => {
	sampleData.value = getTeilnahmen(stundenId).then((res) => {
		sampleData.value = res;
	});
};

onMounted(() => {
	setup();
});

function changeAnwesend(index) {
	sampleData.value[index].anwesend = !sampleData.value[index].anwesend;
	if (!sampleData.value[index].anwesend) {
		sampleData.value[index].note = "-";
	} else {
		sampleData.value[index].note = "";
	}
}
</script>

<style scoped>
@import "@/assets/shared_styles/account_list.css";
.outer-wrapper {
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.button-wrapper {
	display: flex;
	flex-direction: column;
}

button {
	padding: 10px;
	background-color: var(--button-blue);
	color: var(--color-text);
	border: none;
	border-radius: 5px;
	margin-top: 10px;
	margin-left: auto;
}
</style>

<template>
  <div class="outer-wrapper">
    <div class="grid">
        <div class="wrapper">
        <table class="custom_table">
            <thead>
                <tr class="table-header">
                    <th>Name</th>
                    <th>Vorname</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody v-if="soMiNoten !== null">
                <tr v-for="(note, email) in soMiNoten" :key="em ail" @click="showDetails(email)">
                <td>{{ stats[email][0].account.name }}</td>
                <td>{{ stats[email][0].account.vorname }}</td>
                <td>{{ note }}</td>
            </tr>
            </tbody>
            
        </table>
    </div>

    </div>
  </div>
</template>

<script setup>
import { computed, ref, onMounted } from 'vue';
import { getStats } from '@/requests/kurs';
import { useRoute } from 'vue-router';

const route = useRoute();
const kursId = route.params.kurs;

const stats = ref(null);

const soMiNoten = computed(() => {
  let res = {}
  for (let key in stats.value) {
    if (key === stats.value[key][0].stunde.unterricht.kurs.leiter.email) {
      continue;
    }
    let sum = 0;
    let count = 0;
    for (var teilnahme of stats.value[key]) {
      if (teilnahme.note != null) {
        sum += teilnahme.note;
        count++;
      }
    }
    res[key] = sum / count;
  }
  return res
});

async function get() {
  const response = await getStats(kursId);
  if (response === null) {
    console.log('Error');
    return;
  }
  console.log(response);
  stats.value = response;
}

function showDetails(email) {
  route.push(`/kurs/${kursId}/teilnehmer/${email}`);
}


onMounted(() => {
  get();
});

</script>

<style scoped>
@import "../assets/shared_styles/account_list.css";

.outer-wrapper {
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}
.grid {
	display: grid;
}
</style>
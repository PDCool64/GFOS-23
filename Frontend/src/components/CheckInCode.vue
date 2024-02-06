<script setup>
import { ref, onMounted, nextTick } from "vue";

const code = ref(Array.from({ length: 8 }, () => ""));
const inputs = ref([]);

onMounted(() => {
  inputs.value = Array.from({ length: 5 }, (_, i) =>
    document.getElementById(`input-${i}`),
  );
});

const handleInput = (index) => {
  if (index < 4 && inputs.value[index + 1]) {
    nextTick(() => {
      inputs.value[index + 1].focus();
    });
  }
};
const handleDelete = () => {
  const activeElementId = document.activeElement.id;
  const activeIndex = Number(activeElementId.split("-")[1]);
  if (activeIndex === 4 && code.value[4] !== '') {
    code.value[4] = '';
    return;
  }
  const targetIndex = activeIndex > 0 ? activeIndex - 1 : 0;

  code.value[targetIndex] = "";
  nextTick(() => {
    inputs.value[targetIndex].focus();
  });
};

const submitCode = () => {
  console.log(code.value.join(""));
};
</script>

<template>
  <div class="check-in-code">
    <h1>Enter your CheckInCode</h1>
    <form @submit.prevent="submitCode">
      <div class="input-container">
        <input v-for="(item, index) in Array.from({ length: 5 })" :key="index" type="text" maxlength="1"
          v-model="code[index]" @input="handleInput(index)" @keydown.delete="handleDelete" ref="inputs"
          :id="`input-${index}`" />
      </div>
      <button type="submit" style="display: none;"></button>
    </form>
  </div>
</template>

<style scoped>
h1 {
  text-align: center;
  font-size: 2.5rem;
}

.input-container {
  display: flex;
  justify-content: center;
  padding: 10px;
  border-radius: 10px;
}

input {
  width: 60px;
  height: 80px;
  border: 1px solid #000;
  font-size: 2.2rem;
  margin: 0 5px;
  outline: none;
  background-color: var(--fourth-color);
  border: none;
  color: var(--color-text);
  text-align: center;
  box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
  border-radius: 10px;
}

input:focus {
  border: 2px solid var(--color-border-hover);
}
</style>

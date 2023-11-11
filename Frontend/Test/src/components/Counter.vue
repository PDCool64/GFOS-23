
<script setup>

import { ref } from 'vue'
const count = ref(0)

function increment() {
    count.value++
}

</script>

<template>

    <button @click="increment">{{ count }}</button>

</template>

<style scoped>

    button{
        padding: 10px 20px;
        background-color: #999;
        border: none;
        border-radius: 5px;
    }

    button:hover{
        background-color: #666;
        color: #fff;
        font-size: 1.2rem;
    }

</style>
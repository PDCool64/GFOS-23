<template>
    <form @submit.prevent="submitForm" class="login form">
        <h1>{{ header }}</h1>
        <slot></slot>
        <button type="submit">{{ buttonText }}</button>
    </form>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
    header: {
        type: String,
        required: true,
    },
    buttonText: {
        type: String,
        required: true,
    },
    onSubmit: {
        type: Function,
        required: true,
    },
});

const submitForm = async () => {
    await props.onSubmit();
};
</script>

<style scoped>
    @import "../assets/shared_styles/form.css";
</style>
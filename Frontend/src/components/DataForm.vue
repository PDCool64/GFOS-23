<template>
    <form @submit.prevent="submitForm" class="login form">
    <h1>{{ header }}</h1>
        <div v-for="(field, index) in fields" :key="index">
            <input
                :id="field.id"
                v-model="field.model"
                :type="field.type"
                :placeholder="field.placeholder"
                required
            />
        </div>
        <button type="submit">{{ buttonText }}</button>
    </form>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
    header: {
        type: String,
        required: true,
    },
	fields: {
		type: Array,
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

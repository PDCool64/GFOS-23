<template>
	<div class="wrapper">
		<div class="password">
			<CustomForm
				:fields="[
					{
						id: 'old-password',
						model: oldPassword,
						type: 'password',
            placeholder: 'Old Password',
					},
					{
						id: 'new-password',
						model: newPassword,
						type: 'password',
            placeholder: 'New Password',
					},
				]"
        header="Change Password"
				buttonText="Change Password"
				:onSubmit="submitForm"
			/>
			<RouterLink class="link" to="/profile">Cancel</RouterLink>
		</div>
	</div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { changePassword } from "@/requests/account";
import CustomForm from "@/components/CustomForm.vue";

const oldPassword = ref("");
const newPassword = ref("");

const submitForm = async () => {
	if (changePassword(oldPassword.value, newPassword.value)) {
		alert("Password changed successfully.");
		router.push("/profile");
	}
};
</script>
<style scoped>
.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>

<template>
	<div class="stammdaten">
		<h2>Nutzerstammdaten</h2>
		<div v-if="editing" class="editing">
			<p>
				<strong>Username:</strong>
				<input id="username" v-model="user.username" />
			</p>
			<p>
				<strong>Email:</strong>
				<input id="email" v-model="user.email" />
			</p>
			<!-- Add other fields as necessary -->
			<div class="actions">
				<button class="save-button" @click="updateUser">
					<svg
						version="1.1"
						class="save-svg"
						id="Layer_1"
						xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink"
					>
						<g>
							<path
								d="M34.465,67.43c-1.461-1.322-1.574-3.579-0.252-5.041c1.322-1.461,3.58-1.574,5.041-0.252l13.081,11.862l31.088-32.56 c1.361-1.431,3.625-1.487,5.056-0.126c1.431,1.361,1.487,3.624,0.126,5.055L55.11,81.447l-0.005-0.004 c-1.33,1.398-3.541,1.489-4.98,0.187L34.465,67.43L34.465,67.43z M8.792,0h105.296c2.422,0,4.62,0.988,6.212,2.58 s2.58,3.791,2.58,6.212v105.295c0,2.422-0.988,4.62-2.58,6.212s-3.79,2.58-6.212,2.58H8.792c-2.421,0-4.62-0.988-6.212-2.58 S0,116.51,0,114.088V8.792C0,6.371,0.988,4.172,2.58,2.58S6.371,0,8.792,0L8.792,0z M114.088,7.17H8.792 c-0.442,0-0.847,0.184-1.143,0.479C7.354,7.945,7.17,8.35,7.17,8.792v105.295c0,0.442,0.184,0.848,0.479,1.144 c0.296,0.296,0.701,0.479,1.143,0.479h105.296c0.442,0,0.848-0.184,1.144-0.479c0.295-0.296,0.479-0.701,0.479-1.144V8.792 c0-0.443-0.185-0.848-0.479-1.143C114.936,7.354,114.53,7.17,114.088,7.17L114.088,7.17z"
							/>
						</g>
					</svg>
				</button>
				<button class="cancel-button" @click="cancelEdit">
					<svg
						version="1.1"
						class="cancel-svg"
						id="Layer_1"
						xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink"
					>
						<g>
							<path
								d="M8.773,0h105.332c2.417,0,4.611,0.986,6.199,2.574c1.589,1.588,2.574,3.783,2.574,6.199v105.333 c0,2.416-0.985,4.61-2.574,6.199c-1.588,1.588-3.782,2.574-6.199,2.574H8.773c-2.416,0-4.611-0.986-6.199-2.574 C0.986,118.717,0,116.522,0,114.106V8.773c0-2.417,0.986-4.611,2.574-6.199S6.357,0,8.773,0L8.773,0z M80.549,37.291 c1.391-1.392,3.647-1.392,5.039,0s1.392,3.648,0,5.04L66.479,61.439l19.109,19.109c1.392,1.392,1.392,3.647,0,5.04 c-1.392,1.392-3.648,1.392-5.039,0L61.439,66.479L42.33,85.589c-1.392,1.392-3.648,1.392-5.04,0c-1.392-1.393-1.392-3.648,0-5.04 l19.109-19.109L37.291,42.331c-1.392-1.392-1.392-3.648,0-5.04s3.648-1.392,5.04,0L61.439,56.4L80.549,37.291L80.549,37.291z M114.105,7.129H8.773c-0.449,0-0.859,0.186-1.159,0.485c-0.3,0.3-0.486,0.71-0.486,1.159v105.333c0,0.448,0.186,0.859,0.486,1.159 c0.3,0.299,0.71,0.485,1.159,0.485h105.332c0.449,0,0.86-0.187,1.159-0.485c0.3-0.3,0.486-0.711,0.486-1.159V8.773 c0-0.449-0.187-0.859-0.486-1.159C114.966,7.315,114.555,7.129,114.105,7.129L114.105,7.129z"
							/>
						</g>
					</svg>
				</button>
			</div>
		</div>
		<div v-else @click="startEdit">
			<p><strong>Username:</strong> {{ user.username }}</p>
			<p><strong>Email:</strong> {{ user.email }}</p>
			<!-- Display other fields as necessary -->
		</div>
	</div>
</template>

<script>
import { ref } from "vue";
import CheckmarkSvg from "@/assets/svg/check-mark-box-line-icon.svg";
import CrossSvg from "@/assets/svg/close-square-line-icon.svg";
export default {
	name: "UserDetails",
	setup() {
		const user = ref({});
		const fetchUser = async () => {
			const response = await fetch("http://localhost:8080/account/{id}", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					// Include other headers as needed
				},
			});
			if (!response.ok) {
				throw new Error(`HTTP error! status: ${response.status}`);
			}
			const data = await response.json();
			user.value = data;
		};
		const editing = ref(false);

		const startEdit = () => {
			editing.value = true;
		};

		const cancelEdit = () => {
			editing.value = false;
		};

		const updateUser = async () => {
			// Replace this with the actual method to update user data
			console.log("Updating user", user.value);
			editing.value = false;
		};

		fetchUser().then((data) => {
			user.value = data;
		});

		return {
			user,
			editing,
			startEdit,
			cancelEdit,
			updateUser,
			CheckmarkSvg,
			CrossSvg,
		};
	},
};
</script>

<style scoped>

.stammdaten {
    margin: 20px 0;
}
p {
	margin: 0;
}

.editing p {
	display: flex;
	align-items: center;
	gap: 10px;
}

.editing input {
	flex-grow: 1;
	background-color: var(--black-mute);
	border: none;
	border-radius: 20px;
}

.actions {
	display: flex;
	gap: 10px;
}

.material-icons {
	cursor: pointer;
}
.save-svg {
	background-color: var(--vt-c-divider-dark-1);
	fill: var(--checkmark-green);
}

.save-svg,
.cancel-svg {
	border: none;
	cursor: pointer;
    transform: scale(0.2);
}

.cancel-svg {
	border: none;
	cursor: pointer;
	fill: var(--cancel-red);
}

.save-button,
.cancel-button {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 20px;
  width: 50%;
  padding: 0;
  border: none;
  background: none;
  margin-top: 10px;
}
</style>

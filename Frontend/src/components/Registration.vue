<script setup>
import { ref } from 'vue';

const vorname = ref('');
const nachname = ref('');
const email = ref('');
const password = ref('');
const geburtstag = ref('');

const error = ref(false);

const errorMessage = ref('');

const submitForm = async () => {

    console.log({
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'password': password.value,
        },
        body: JSON.stringify({
            vorname: vorname.value,
            name: nachname.value,
            email: email.value,
            geburtsdatum: geburtstag.value,
            isadmin: false,
            kursList: [],
            kursList1: [],
            meldungList: [],
            personalnummer: null,
            timestampletzteaktion: null,
            unterrichtList: [],
            wochenstunden: null, 
        })
    });


    const response = await fetch('http://localhost:8080/Backend/account', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'password': password.value,
            'mode': 'no-cors'
        },
        body: JSON.stringify({
            vorname: vorname.value,
            name: nachname.value,
            email: email.value,
            geburtsdatum: geburtstag.value + "T00:00:00Z[UTC]",
            isadmin: false,
            kursList: [],
            kursList1: [],
            meldungList: [],
            personalnummer: null,
            timestampletzteaktion: null,
            unterrichtList: [],
            wochenstunden: null
        })
    });

    if (response.status === 400) {
        errorMessage.value = "Ein Account mit dieser Email existiert bereits.";
        error.value = true;
    } else {
        const data = await response.json();
        console.log(data);
        errorMessage.value = "Registrierung erfolgreich.";
    }
};
</script>

    <template>
        <div class="registration form">
            <h1>Registrierung</h1>
            <form @submit.prevent="submitForm">
                <input v-model="vorname" type="text" id="vorname" name="vorname" placeholder="Vorname" required>
                <input v-model="nachname" type="text" id="nachname" name="nachname" placeholder="Nachname" required>
                <input v-model="email" type="email" id="email" name="email" placeholder="Email" required>
                <input v-model="password" type="password" id="password" name="password" placeholder="Password" required>
                <input v-model="geburtstag" type="date" id="geburtstag" name="geburtstag" placeholder="Geburtsdatum" required>
            <button type="submit">Register</button>
            <p :class="{'error-message': error, 'success-message': !error}">{{ errorMessage }}</p>
            Already have an account? <RouterLink to="/login">Login</RouterLink>
        </form>
    </div>
</template>

<style scoped>
    @import '../assets/shared_styles/form.css';
</style>
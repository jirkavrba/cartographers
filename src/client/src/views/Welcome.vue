<template>
  <div class="home">
    <h2>Welcome! Please choose an username and click next</h2>

    <label for="username">Username:</label>
    <input type="text" id="username" v-model="username">
    <small>Username must have at least 3 characters</small>

    <hr>

    <button @click="login" :disabled="username.length < 3">Next</button>
  </div>
</template>

<script>

import {v4 as uuid} from 'uuid';

export default {
  name: "Welcome",
  data: () => ({
    username: ""
  }),
  methods: {
    login() {
      this.$router.replace("/games");
      this.$store.commit("login", {
        id: uuid(),
        username: this.username
      })
    }
  },
  components: {}
}
</script>

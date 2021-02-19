<template>
  <div v-if="$store.state.username != null">
    <p>
      Current user:
      <b>{{ $store.state.username }}</b><br>
      <code>{{ $store.state.id }}</code>
    </p>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  name: 'CurrentUser',
  methods: {
    logout() {
      this.$store.commit('logout');
      this.$router.replace("/");
    }
  }
}
</script>

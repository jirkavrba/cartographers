<template>
  <div id="app">
    <h1>Cartographers</h1>
    <CurrentUser/>
    <router-view/>
  </div>
</template>
<script>

import CurrentUser from "@/components/CurrentUser";

export default {
  name: "App",
  components: {
    CurrentUser
  }
}
</script>
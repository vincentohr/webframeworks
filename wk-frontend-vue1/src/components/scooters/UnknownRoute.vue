<template>
  <h2>
    Not found go back to
    <router-link :to="goToHomePage">Welcome Page</router-link>
  </h2>
</template>

<script>
export default {
  name: 'UnknownRoute',

  computed: {
    goToHomePage () {
      return '/home'
    }
  }
}
</script>

<style scoped>
h2 {
  color: orangered;
}
</style>

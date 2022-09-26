<template>
<app-header></app-header>
<app-nav-bar></app-nav-bar>
  <router-view></router-view>
</template>

<script>
import Header from '@/components/Header.vue'
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'App33',
  data () {
  },
  components: {
    'app-header': Header,
    'app-nav-bar': NavBar
  }
}
</script>

<style scoped>

</style>

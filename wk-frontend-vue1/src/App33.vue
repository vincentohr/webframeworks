<template>
<app-header></app-header>
<app-nav-bar></app-nav-bar>
  <Detail32></Detail32>
  <router-view></router-view>
</template>

<script>
import Header from '@/components/Header.vue'
import NavBar from '@/components/NavBar.vue'
// import Detail32 from '@/components/scooters/Detail32'

export default {
  name: 'App33',
  data () {
  },
  components: {
    'app-header': Header,
    'app-nav-bar': NavBar
    // Detail32
  }
}
</script>

<style scoped>

</style>

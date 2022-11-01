<template>
  <app-header></app-header>
  <app-nav-bar></app-nav-bar>
  <router-view></router-view>
</template>

<script>
import Header from '@/components/Header.vue'
import NavBar from '@/components/NavBar.vue'
import { ScooterAdaptor } from '@/models/ScooterAdaptor'
import CONFIG from '@/app-config'

export default {
  name: 'App37',
  data () {
  },
  components: {
    'app-header': Header,
    'app-nav-bar': NavBar
  },
  provide () {
    return {
      scooterService: new ScooterAdaptor(CONFIG.BACKEND_URL + '/scooters')
    }
  }
}
</script>

<style scoped>

</style>

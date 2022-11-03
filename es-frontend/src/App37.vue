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
  components: {
    'app-header': Header,
    'app-nav-bar': NavBar
  },
  provide: {
    scooterService: new ScooterAdaptor(CONFIG.BACKEND_URL + '/scooters')
  }
  // provide() {
  //   // use function syntax so that we can access `this`
  //   return {
  //     scooterService: new ScooterAdaptor(CONFIG.BACKEND_URL + '/scooters')
  //   }
  // }
}
</script>

<style scoped>

</style>

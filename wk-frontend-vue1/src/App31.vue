<template>
  <Header></Header>
  <NavBar></Navbar>
<!--  <Welcome msg=""/>-->
<!--  <ScootersOverview31></ScootersOverview31>-->
  <Overview32></Overview32>
</template>

<script>
import Header from './components/Header.vue'
import Welcome from './components/Welcome.vue'
import NavBar from '@/components/NavBar'
import ScootersOverview31 from '@/components/scooters/ScootersOverview31'
import Overview32 from '@/components/scooters/Overview32'
import Detail32 from '@/components/scooters/Detail32'

export default {
  name: 'App31',
  components: {
  //   ScootersOverview31,
    NavBar,
    Overview32,
    Header
    // eslint-disable-next-line vue/no-unused-components
    // Welcome
  }
}
</script>

<style scoped>
/*#app {*/
/*!*  font-family: Avenir, Helvetica, Arial, sans-serif;/*/
/*/  -webkit-font-smoothing: antialiased;/*/
/*/  -moz-osx-font-smoothing: grayscale;/*/
/*/  text-align: center;/*/
/*/  color: #2c3e50;/*/
/*/  margin-top: 60px;/*/
/*/}*!*/
</style>

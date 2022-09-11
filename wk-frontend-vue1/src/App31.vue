<template>
  <Header msg=""/>
  <NavBar msg=""/>
  <Welcome msg=""/>
</template>

<script>
import Header from './components/Header.vue'
import Welcome from './components/Welcome.vue'
import NavBar from '@/components/NavBar'

export default {
  name: 'App31',
  components: {
    NavBar,
    Header,
    Welcome
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

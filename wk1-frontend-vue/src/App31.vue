<template>
  <Header msg="Header component works!"/>
  <Welcome msg="Welcome Component works!"/>
</template>

<script>
import Header from './components/Header.vue'
import Welcome from './components/Welcome.vue'

export default {
  name: 'App31',
  components: {
    Header,
    Welcome
  }
}
</script>

<style scoped>
/*#app {*/
/*  font-family: Avenir, Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*  margin-top: 60px;*/
/*}*/
</style>

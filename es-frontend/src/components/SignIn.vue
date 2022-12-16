<template>
<!--  <table>-->
<!--    <tr>-->
<!--      <th><label>User e-mail: </label></th>-->
<!--      <th><input type="text"></th>-->
<!--    </tr>-->
<!--    <tr>-->
<!--      <th><label>Password:</label></th>-->
<!--      <th><input type="password"></th>-->
<!--    </tr>-->
<!--  </table>-->
<h1>Please provide your login credentials:</h1>
<!--  method="post" v-on:@submit.prevent="SignIn"-->
  <form >
    <table>
      <tr>
        <th>User e-mail</th>
        <th><input type="email" v-model="email"></th>
      </tr>
      <tr>
        <th>Password</th>
        <th><input type="password" v-model="password"></th>
      </tr>
    </table>
<!--    <input type="submit" value="Sign In">-->
    <button @click="SignIn">click me!</button>
  </form>
  <h3>Current token:</h3>
  <div class="token-box">{{sessionService.currentToken}}</div>
</template>

<script>

export default {
  name: 'SignIn',
  inject: ['sessionService'],
  data () {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async SignIn () {
      console.log('TEST')
      await this.sessionService.asyncSignIn(this.email, this.password)
    }
  }
}
</script>

<style scoped>
form{
  color: black;
}
h1, h3{
  color: black;
}
table,th,td {
  border: 1px solid;
}
table{
    display: table;
    color: black;
}
.token-box{
  color: black;
}
</style>

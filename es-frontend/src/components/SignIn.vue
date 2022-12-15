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
  <form method="POST" v-on:@submit.prevent="SignIn()">
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
    <input type="submit" value="Sign In">
  </form>
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
      await this.theSessionService.asyncSignIn(this.email, this.password)
    }
  }
}
</script>

<style scoped>
form{
  color: black;
}
h1{
  color: black;
}
table,th,td {
  border: 1px solid;
}
table{
    display: table;
    color: black;
}

</style>

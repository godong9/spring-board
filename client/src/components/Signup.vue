
<template>
  <div class="signup">
    <h1>{{ msg }}</h1>
    <h2>Signup Page</h2>
    <div>
      <input type="text" v-model="name" placeholder="Name">
      <input type="text" v-model="email" placeholder="Email">
      <input type="password" v-model="password" placeholder="Password">
      <button v-on:click="clickSignup">Signup</button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'signup',
    data() {
      return {
        msg: 'Signup page Message',
        name: '',
        email: '',
        password: '',
      };
    },
    methods: {
      clickSignup: function clickSignup() {
        this.$http.post('http://localhost:9000/users/signup', { name: this.name, email: this.email, password: this.password }).then((response) => {
          // get body data
          this.bodyData = response.body;
          console.log(this.bodyData);
        }, (response) => {
          this.errorData = response.body;
          // error callback
        });
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
    font-size: 50px;
  }

</style>

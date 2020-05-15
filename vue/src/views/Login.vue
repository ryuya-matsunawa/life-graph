<template>
  <div class="background">
    <div class="login">
      <img id="title" src="../assets/signin.png">
      <div class="form-item">
        <label for="email" />
        <input type="email" name="email" required="required" placeholder="Email Address">
        <label for="password" />
        <input type="password" name="password" required="required" placeholder="Password">
      </div>
      <div class="button-panel">
        <input type="submit" class="button" title="Sign In" value="Sign In">
      </div>
      <div class="form-footer">
        <p><a href="#">Create an account</a></p>
        <p><a href="#">Forgot password?</a></p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/*Font*/
@import url(//weloveiconfonts.com/api/?family=fontawesome);
[class*="fontawesome-"]:before {
  font-family: 'FontAwesome', sans-serif;
}
/* Simple Reset */
* {
  margin: 0;
  padding: 0;
}

.background {
  background-image: url("../assets/team20.png");
  background-size: cover;
  width: 100%;
  height: 500px;
}

.login {
  width: 50%;
  margin-left: 80px;
}

#title {
  width: 40%;
  margin-top: 30px;
}

.form-item {
  margin-bottom: 0.75em;
}

.form-item input {
  background-color: transparent;
  border: none;
  border-bottom: 2px solid #e9e9e9;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 1em;
  height: 50px;
  transition: border-color 0.3s;
  width: 50%;
  display: block;
  text-align: center;
  z-index: -1;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

.button-panel {
  margin: 2em 0 0;
  margin-top: 40px;
  text-align: left;
}

.button-panel .button {
  background: #EEFF41;
  border: none;
  color: #8c8c8c;
  cursor: pointer;
  height: 45px;
  font-family: 'Open Sans', sans-serif;
  font-size: 1.1em;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  transition: background 0.3s ease-in-out;
  width: 25%;
}

.button:hover {
  background: #FF8F00;
}

.form-footer {
  font-size: 1em;
  padding: 2em 0;
  text-align: left;
}

.form-footer a {
  color: #8c8c8c;
  text-decoration: none;
  transition: border-color 0.3s;
}

.form-footer a:hover {
  border-bottom: 1px dotted #8c8c8c;
}
</style>

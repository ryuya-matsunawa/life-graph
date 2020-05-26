<template>
  <div id="passchange">
    <div class="file">
      <div class="formOut">
        <li class="current">
          現在のPassword
          <input type="text">
        </li>
        <li class="newpass">
          新しいパスワード
          <input type="text">
        </li>
        <button class="createButton">
          変更
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.file {
  width: 600px;
  margin: 0 auto;
  margin-top: 30px;
  border: 1px solid;
  padding: 30px;
  border-radius: 8px;
  border-color: #c0c0c0;
  width: 50%;
  background-color:  #efeeee;
}

.formOut{
  display: inline-block;
  text-align: center;
  width: 250px;
  font-weight: bold;
  font-size: 18px;
  font-family: 'Noto Serif JP', serif;
  list-style-type: none;
}

.formOut input {
  margin: 20px 10px;
}

.createButton {
  padding: 10px 20px;
  color:#353434;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:10px;
  cursor: pointer;
  font-family: 'Hannari', serif;
  border-radius: 8px;
}

.createButton:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.form-item input {
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #8c8c8c;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 16px;
  height: 50px;
  transition: border-color 0.3s;
  width: 280px;
  display: block;
  margin-left: 180px;
  margin-top: 10px;
  text-align: center;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

</style>

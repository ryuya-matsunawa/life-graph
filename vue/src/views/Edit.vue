<template>
  <div>
    <form action="/my-handling-form-page" method="post">
      <ul>
        <li>
          <label for="age">年齢</label>
          <input id="age" type="int" name="age">
        </li>
        <li>
          <label for="score">スコア</label>
          <input id="score" type="int" name="score">
        </li>
        <li>
          <label for="comment">コメント</label>
          <input id="comment" type="varchar(255)" name="comment">
        </li>
      </ul>
      <button type="submit">
        登録
      </button>
      <button type="submit">
        編集
      </button>
    </form>
    <button onclick="location.href='./">
      遷移
    </button>
  </div>
</template>

<style>
ul {
  list-style: none;
}
</style>

<template>
  <Header />
  <br><br><br><br><br>
  <div class="container" id="container">
  <div class="form-container sign-in-container">
    <div class="form-floating">
      <h1>Sign In</h1>
      <div class="social-container">
        <a href="#" class="social"><div id="naver_id_login"></div></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" class="social"><i class="fa fa-google-plus" aria-hidden="true" @click="googleLoginBtn"></i></a>
        &nbsp;&nbsp;&nbsp;
        <a href="#" class="social"><i class="fa fa-comments" aria-hidden="true"></i></a>
      </div>
      <span>or use your account</span>
      <input type="id" placeholder="ID" v-model="state.form.id">
      <input type="password" placeholder="Password" v-model="state.form.password" /><br>
      <button @click="submit()">로그인</button>
    </div>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-right">
          <h1>VeganRoadMap</h1>
          <p>비건로드맵에 가게를 등록하고 싶으신가요?</p>
          <button class="ghost" id="signUp" @click="$router.push('/join2')">회원가입</button>
        </div>
      </div>
    </div>
  </div>
  <br><br><br><br><br>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";
import Header from "@/components/header/Header.vue";

export default {
  components: {Header},
  mounted(){
    const naver_id_login = new window.naver_id_login("a4TEdRYQwTEos4Ljj4RU", "http://localhost:3000");
    const state = naver_id_login.getUniqState();
    naver_id_login.setButton("white", 2,40); // 버튼 설정
    naver_id_login.setState(state);
    naver_id_login.init_naver_id_login();
  },
  setup() {
    const state = reactive({
      form :{
        id: "",
        password: ""
      }
    })
    const submit = () => {
      axios.post("/api/store/login", state.form).then((res)=> {
        store.commit("setAccount", res.data); // store 저장
        if (res.data == 0) {
          if (state.form.id == "") {
            window.alert("아이디를 입력해주세요");
          } else if (state.form.password == "") {
            window.alert("비밀번호를 입력해주세요");
          } else {
            window.alert("아이디나 비밀번호가 틀립니다");
            router.push({path: "/login2"});
          }
        }else {
          window.alert("로그인 하였습니다");
          router.push({path: "/home_store"});
        }
      })
    }
    return {state, submit}
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

* {
  box-sizing: border-box;
}

body {
  background: #f6f5f7;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  margin: -20px 0 50px;
}

h1 {
  font-weight: bold;
  margin: 0;
}

h2 {
  text-align: center;
}

p {
  font-size: 14px;
  font-weight: 100;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

button {
  border-radius: 20px;
  border: 1px solid darkolivegreen;
  background-color: darkolivegreen;
  color: #FFFFFF;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #FFFFFF;
}

.form-floating {
  background-color: #FFFFFF;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  text-align: center;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0,0,0,0.25),
  0 10px 10px rgba(0,0,0,0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%, 49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%, 100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.container.right-panel-active .overlay-container{
  transform: translateX(-100%);
}

.overlay {
  background: #749a5b;
  background: -webkit-linear-gradient(to right, #7b814e, #58723f);
  background: linear-gradient(to right, #0a3627, #545d32);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: #FFFFFF;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  text-align: center;
  top: 0;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #DDDDDD;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

footer {
  background-color: #222;
  color: #fff;
  font-size: 14px;
  bottom: 0;
  position: fixed;
  left: 0;
  right: 0;
  text-align: center;
  z-index: 999;
}

footer p {
  margin: 10px 0;
}

footer i {
  color: red;
}

footer a {
  color: #3c97bf;
  text-decoration: none;
}
</style>
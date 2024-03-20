import { createRouter, createWebHistory } from "vue-router";
import HomeComponent from "@/components/Home/HomeComponent.vue";
import LoginComponent from "@/components/Login/LoginComponent.vue";
import TrainerSignupComponent from "@/components/Signup/TrainerSignupComponent.vue";
import MemberSignupComponent from "@/components/Signup/MemberSignupComponent.vue";
import SignupRoleComponent from "@/components/Signup/SignupRole.vue";
import LoginSuccessComponent from "@/components/Login/LoginSuccessComponent.vue";


const routes = [
  {
    // URL 경로 지정
    path: "/",
    // 이 라우터의 이름
    name: "HOME",
    component: HomeComponent,
  },
  {
    path: "/login",
    name: "LoginComponent",
    component: LoginComponent,
  },
  {
    path: "/signupRole",
    name: "SignupRole",
    component: SignupRoleComponent,
  },
  {
    path: "/signupTrainer",
    name: "TrainerSignupComponent",
    component: TrainerSignupComponent,
  },
  {
    path: "/signupMember",
    name: "MemberSignupComponent",
    component: MemberSignupComponent,
  },
  {
    path: "/loginSuccess",
    name: "LoginSuccessComponent",
    component: LoginSuccessComponent,
  },
];
const router = createRouter({
  // vue-router는 내부적으로 두 가지 방식의 history 관리를 제공한다
  // 1) createWebHistory -> 빈번하게 사용
  // 2) createHashHistory
  history: createWebHistory(),
  routes,
});

export default router;

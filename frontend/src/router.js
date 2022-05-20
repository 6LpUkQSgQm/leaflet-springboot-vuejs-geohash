import Vue from "vue";
import Router from "vue-router";
import LeafletMap from "@/components/LeafletMap";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "LeafletMap",
      component: LeafletMap,
    },
  ],
});

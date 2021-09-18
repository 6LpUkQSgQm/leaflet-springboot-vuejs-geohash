<template>
  <v-app>
    <LeafletMap/>
  </v-app>
</template>

<script>
import LeafletMap from "./components/LeafletMap";

export default {
  name: 'App',

  components: {
    LeafletMap
  },
};
</script>

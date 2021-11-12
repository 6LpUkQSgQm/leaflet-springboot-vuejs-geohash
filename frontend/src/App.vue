<template style="background-color:#edf2f4">
  <v-app>
    <v-container fluid>
      <v-row>
        <v-col style="padding: 30px" md="6" :cols="12">
          <LeafletMap id="map" />
        </v-col>
        <v-col md="6" :cols="12">
          <GeoHashForm />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import LeafletMap from "./components/LeafletMap";
import GeoHashForm from "./components/GeoHashForm";

export default {
  name: "App",
  components: {
    LeafletMap,
    GeoHashForm,
  },
  created() {
    console.log('passage 1')
    this.$store.dispatch("geohashs/getAllGeohashs");
  },
};
</script>
<style scoped>
#map {
  height: 93vh !important;
}
</style>

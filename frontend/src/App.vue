<template>
  <v-app >
    <v-container fluid>
      <v-row>
        <v-col :cols="6">
          <LeafletMap  style="height:97vh !important"/>
        </v-col>
        <v-col :cols="6">
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
};
</script>

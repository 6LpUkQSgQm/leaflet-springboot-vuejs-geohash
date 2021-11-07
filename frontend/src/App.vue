<template style="background-color:#edf2f4">
  <v-app>
    <v-container fluid>
      <v-row>
        <v-col :cols="6">
          <LeafletMap style="height:97vh !important" />
          <SmallLeafletMap style="position:absolute;bottom:15px;left:15px;margin:2px;" />
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
import SmallLeafletMap from "./components/SmallLeafletMap";
import GeoHashForm from "./components/GeoHashForm";
export default {
  name: "App",

  components: {
    LeafletMap,
    GeoHashForm,
    SmallLeafletMap,
  },
};
</script>

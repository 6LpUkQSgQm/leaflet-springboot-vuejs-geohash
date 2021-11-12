<template>
  <div>
    <v-container>
      <h1>GEOHASH v0.1</h1>
      <v-divider class="mt-5 mb-5"></v-divider>
      <v-container>
        <p>
          Enter latitude, longitude & precision to obtain geohash; enter geohash
          to obtain latitude/longitude
        </p>
        <v-form>
          <v-row
            ><v-col cols="12" md="3">
              <v-text-field
                v-model="countryCode"
                label="countryCode"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field v-model="name" label="name" required></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="latitude"
                label="latitude"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="longitude"
                label="longitude"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-btn
            v-on:click="postGeohash(name, latitude, longitude)"
            class="right"
            color="#ef233c"
            style="color:#edf2f4"
            elevation="2"
            >Submit</v-btn
          >
        </v-form>
      </v-container>

      <v-divider class="mt-5 mb-5"></v-divider>
      <v-container>
        <p>List of Geohashs</p>
        <v-data-table
          :headers="headers"
          :items="geohashs"
          :items-per-page="20"
          class="elevation-1"
        ></v-data-table>
      </v-container>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "GeoHashForm",
  components: {},
  data() {
    return {
      headers: [
        {
          text: "countryCode",
          align: "start",
          value: "countryCode",
        },
        { text: "name", value: "name" },
        { text: "latitude", value: "latitude" },
        { text: "longitude", value: "longitude" },
        { text: "geohash", value: "geohashValue" },
        { text: "delete", value: false },
      ],
    };
  },
   computed: mapState({
    geohashs: state => state.geohashs.all,
    longitude: state => state.geohashs.longitude,
    latitude: state => state.geohashs.latitude,
    countryCode: state => state.geohashs.countryCode
  }),
  methods: {
    postGeohash(name, latitude, longitude) {
      console.log(name, latitude, longitude);
      axios({
        method: "POST",
        url: process.env.VUE_APP_URL_BACKEND,
        data: {
          query: `
                mutation {
              createGeohash(name: "${name}", latitude:"${latitude}", longitude:"${longitude}"){
              id name latitude longitude geohashValue
              }
        }`,
        },
      })
        .then((response) => {
          this.data = response.data.data.findAllGeohashs;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped></style>

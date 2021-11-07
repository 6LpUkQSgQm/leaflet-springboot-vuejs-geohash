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
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field v-model="name" label="name" required></v-text-field>
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="latitude"
                label="latitude"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="4">
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
          :items="data"
          :items-per-page="20"
          class="elevation-1"
        ></v-data-table>
      </v-container>
    </v-container>
  </div>
</template>

<script>
import { AXIOS } from "./http-common";
import axios from "axios";
export default {
  name: "GeoHashForm",
  components: {},
  data() {
    return {
      headers: [
        {
          text: "name",
          align: "start",
          value: "name",
        },
        { text: "latitude", value: "latitude" },
        { text: "longitude", value: "longitude" },
        { text: "geohash", value: "geohashValue" },
      ],
      data: [],
    };
  },
  created() {
    axios({
      method: "POST",
      url: "http://localhost:8093/api/v1/graphql",
      data: {
        query: `
                {
              findAllGeohashs{
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
  methods: {
    postGeohash(name, latitude, longitude) {
      console.log(name, latitude, longitude);
      axios({
        method: "POST",
        url: "http://localhost:8093/api/v1/graphql",
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

<template>
  <div>
    <v-container>
      <h1>GEOHASH v0.1</h1>
      <v-divider class="mt-5 mb-5"></v-divider>
      <v-container>
        <p>Enter latitude, longitude & precision to obtain geohash</p>
        <v-form ref="form">
          <v-row
            ><v-col cols="12" md="3">
              <v-text-field
                v-model="countryCode"
                :rules="countryCodeRules"
                label="countryCode"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="name"
                :rules="nameRules"
                label="name"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="latitude"
                :rules="latitudeRules"
                label="latitude"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="longitude"
                :rules="longitudeRules"
                label="longitude"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-btn
            v-on:click="postGeohash(countryCode, name, latitude, longitude)"
            class="right"
            color="#ef233c"
            style="color: #edf2f4"
            elevation="2"
            >Submit</v-btn
          >
        </v-form>
      </v-container>

      <v-divider class="mt-5 mb-5"></v-divider>
      <v-container>
        <h3>List of Geohashs</h3>
        <div style="max-height: 50vh; overflow: auto">
          <v-data-table
            :headers="headers"
            :items="geohashs"
            :items-per-page="5"
            class="elevation-1"
          >
            <template slot="item.delete" scope="props">
              <v-icon small @click="openDialogDeleteGeohash(props.item.id)">
                mdi-delete
              </v-icon></template
            >
          </v-data-table>
        </div>
      </v-container>
    </v-container>
    <v-dialog style="z-index: 999999" v-model="dialog" width="500">
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Delete geohash
        </v-card-title>

        <v-card-text> Are you sure to delete this geohash ? </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="dialog = false"> Annuler </v-btn>
          <v-btn color="red" text @click="confirmDeleteGeohash()">
            Delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "GeoHashForm",
  components: {},
  data() {
    return {
      dialog: false,
      idGeohashToDelete: null,
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
        { text: "delete", value: "delete" },
      ],
      name: "",
      nameRules: [(v) => !!v || "Name is required"],
      latitudeRules: [(v) => !!v || "Latitude is required"],
      longitudeRules: [(v) => !!v || "Longitude is required"],
      countryCodeRules: [(v) => !!v || "CountryCode is required"],
    };
  },
  computed: mapState({
    geohashs: (state) => state.geohashs.all,
    longitude: (state) => state.geohashs.longitude,
    latitude: (state) => state.geohashs.latitude,
    countryCode: (state) => state.geohashs.countryCode,
  }),
  methods: {
    openDialogDeleteGeohash(id) {
      this.idGeohashToDelete = id;
      this.dialog = true;
    },
    confirmDeleteGeohash() {
      console.log(this.idGeohashToDelete, "idGeohashToDelete");
      this.$store.dispatch("geohashs/deleteGeohash", {
        geohash: { id: this.idGeohashToDelete },
      });
      this.dialog = false;
    },
    postGeohash(countryCode, name, latitude, longitude) {
      const test = this.$refs.form.validate();
      if (test) {
        this.$store.dispatch("geohashs/postGeohash", {
          geohash: { countryCode, name, latitude, longitude },
        });
      }
    },
  },
};
</script>
<style scoped></style>

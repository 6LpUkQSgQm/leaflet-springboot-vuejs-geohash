<template>
  <div>
    <h1>GEOHASH v0.1</h1>
    <v-divider></v-divider>
    <p>
      Enter latitude, longitude & precision to obtain geohash; enter geohash to
      obtain latitude/longitude
    </p>
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col cols="12" md="4">
            <v-text-field
              v-model="firstname"
              :rules="nameRules"
              :counter="10"
              label="First name"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="lastname"
              :rules="nameRules"
              :counter="10"
              label="Last name"
              required
            ></v-text-field>
          </v-col>

          <v-col cols="12" md="4">
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
export default {
  name: "GeoHashForm",
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>
<style scoped></style>

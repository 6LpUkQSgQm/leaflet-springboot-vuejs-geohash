import axios from "axios";
// initial state
const state = () => ({
  all: [],
  countryCode: "",
  latitude: "",
  longitude: "",
});

// getters
const getters = {};

// actions
const actions = {
  postGeohash({ commit }, data) {
    axios({
      method: "POST",
      url: process.env.VUE_APP_URL_BACKEND,
      data: {
        query: `
            mutation {
          createGeohash(countryCode: "${data.geohash.countryCode}", name: "${data.geohash.name}", latitude:"${data.geohash.latitude}", longitude:"${data.geohash.longitude}"){
          id countryCode name latitude longitude geohashValue createdDate
          }
    }`,
      },
    })
      .then((response) => {
        commit("setGeohash", response.data.data.createGeohash);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteGeohash({ commit }, data) {
    axios({
      method: "POST",
      url: process.env.VUE_APP_URL_BACKEND,
      data: {
        query: `
            mutation {
          deleteGeohash(id: "${data.geohash.id}")
    }`,
      },
    })
      .then((response) => {
        if (response.data) commit("deleteGeohash", data.geohash.id);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  getCountryGeocoding({ commit }, latlng) {
    axios({
      method: "GET",
      url: `${process.env.VUE_APP_URL_API_REVERSE_GEOCODE}?latitude=${latlng.lat}&longitude=${latlng.lng}`,
    })
      .then((response) => {
        commit("setCountryCode", response.data.countryCode);
        commit("setLatLong", latlng);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  getAllGeohashs({ commit }) {
    axios({
      method: "POST",
      url: process.env.VUE_APP_URL_BACKEND,
      data: {
        query: `
              {
            findAllGeohashs{
            id countryCode name latitude longitude geohashValue createdDate
        }
      }`,
      },
    })
      .then((response) => {
        commit("setGeohashs", response.data.data.findAllGeohashs);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};

// mutations
const mutations = {
  setGeohashs(state, geohashs) {
    state.all = geohashs;
  },
  deleteGeohash(state, id) {
    state.all.forEach((geohash, index) => {
      console.log(geohash.id === id, id);
      if (geohash.id === id) state.all.splice(index, 1);
    });
  },
  setGeohash(state, geohash) {
    state.all.push(geohash);
  },
  setCountryCode(state, countryCode) {
    state.countryCode = countryCode;
  },
  setLatLong(state, latLongCountry) {
    state.latitude = latLongCountry.lat;
    state.longitude = latLongCountry.lng;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

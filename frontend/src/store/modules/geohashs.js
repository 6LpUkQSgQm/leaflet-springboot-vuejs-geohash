import { AXIOS } from "../../components/http-common";
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
  getCountryGeocoding({ commit }, latlng) {
    axios({
      method: "GET",
      url: `${process.env.VUE_APP_URL_API_REVERSE_GEOCODE}?latitude=${latlng.lat}&longitude=${latlng.long}`,
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
    console.log("passage");
    axios({
      method: "POST",
      url: process.env.VUE_APP_URL_BACKEND,
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

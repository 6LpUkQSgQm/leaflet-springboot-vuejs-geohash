import { AXIOS } from "../../components/http-common";
import axios from "axios";
// initial state
const state = () => ({
  all: [],
  countryCode: "",
  latLongCountry: [],
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
      })
      .catch((error) => {
        console.log(error);
      });
  },
  addLatLong({ commit }, latLongCountry) {
    commit("setLatLong", latLongCountry);
  },
  getAllGeohashs({ commit }) {
    axios({
      method: "POST",
      url: `${process.env.VUE_APP_URL_BACKEND}`,
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
    state.latLongCountry = latLongCountry;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

<template>
  <l-map
    @ready="onReady"
    @locationfound="onLocationFound"
    @click="getLatLong"
    :center="center"
    :zoom="zoom"
    class="map cursor"
    ref="map"
    @update:zoom="zoomUpdated"
    @update:center="centerUpdated"
  >
    <l-tile-layer :url="url"> </l-tile-layer>
    <l-marker
      v-for="(geohash, index) in geohashs"
      :lat-lng="[geohash.longitude, geohash.latitude]"
      :key="index"
      :icon="iconTriangle"
    />
    <l-marker :lat-lng="marker" :icon="iconCircle"></l-marker>
  </l-map>
</template>

<script>
import { mapState} from 'vuex'
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer, LMarker, LIcon } from "vue2-leaflet";
import { icon } from "leaflet";
export default {
  name: "LeafletMap",
  components: {
    LMap,
    LTileLayer,
    LMarker,
  },
  data() {
    return {
      data: [],
      message: "",
      url:
        "https://tiles.stadiamaps.com/tiles/alidade_smooth_dark/{z}/{x}/{y}{r}.png",
      center: [48.8534, 2.3488],
      zoom: 13,
      marker: [48.8534, 2.3488],
      iconCircle: icon({
        iconUrl: require("../assets/circle.png"),
        iconSize: [20, 20],
      }),
      iconTriangle: icon({
        iconUrl: require("../assets/triangle.png"),
        iconSize: [20, 20],
      }),
    };
  },
  computed: mapState({
    geohashs: state => state.geohashs.all
  }),
  methods: {
    getLatLong(event) {
      this.$store.dispatch("geohashs/getCountryGeocoding", event.latlng);
    },
    onReady(mapObject) {
      mapObject.locate();
    },
    onLocationFound(location) {
      this.center = [location.latitude, location.longitude];
      this.marker = [location.latitude, location.longitude];
    },
    zoomUpdated(zoom) {
      this.zoom = 13;
    },
    centerUpdated(center) {
      this.center = center;
    },
    zoomUpdated(zoom) {
      this.zoom = 13;
    },
    centerUpdated(center) {
      this.center = center;
    },
  },
};
</script>
<style scoped>
.map {
  height: 100vh !important;
}
.cursor {
  cursor: crosshair !important;
}
</style>

<template>
  <l-map
    @ready="onReady"
    @locationfound="onLocationFound"
    :center="center"
    :zoom="zoom"
    :options="{ zoomControl: false }"
    class="map"
    ref="map"
    @update:zoom="zoomUpdated"
    @update:center="centerUpdated"
  >
    <l-tile-layer :url="url"> </l-tile-layer>
    <l-marker
        :lat-lng="[47.41322, -1.209482]"
        :icon="iconTriangle"
      />
    <l-marker :lat-lng="marker" :icon="iconCircle"></l-marker>
  </l-map>
</template>

<script>
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer, LMarker, LIcon } from "vue2-leaflet";
import { icon } from "leaflet";

export default {
  name: "LeafletMap",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LIcon,
  },
  data() {
    return {
      url:
        "https://tiles.stadiamaps.com/tiles/alidade_smooth_dark/{z}/{x}/{y}{r}.png",
      center: [48.8534, 2.3488],
      zoom: 0,
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
  methods: {
    onReady(mapObject) {
      mapObject.locate();
    },
    onLocationFound(location) {
      this.center = [location.latitude, location.longitude];
      this.marker = [location.latitude, location.longitude];
    },
    zoomUpdated(zoom) {
      this.zoom = 1;
    },
    centerUpdated(center) {
      this.center = center;
    },
  },
};
</script>
<style scoped>
.map {
  width: 300px !important;
  height: 200px !important;
}
</style>

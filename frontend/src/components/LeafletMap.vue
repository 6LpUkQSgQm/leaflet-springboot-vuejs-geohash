<template>
  <l-map
    @ready="onReady"
    @locationfound="onLocationFound"
    :center="center"
    :zoom="zoom"
    class="map"
    ref="map"
    @update:zoom="zoomUpdated"
    @update:center="centerUpdated"
  >
    <l-tile-layer :url="url"> </l-tile-layer>
    <l-marker :draggable="true" :lat-lng="marker"></l-marker>
  </l-map>
</template>

<script>
import { AXIOS } from "./http-common";
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
   iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
   iconUrl: require('leaflet/dist/images/marker-icon.png'),
   shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
});

export default {
  name: "LeafletMap",
  components: {
    LMap,
    LTileLayer,
    LMarker
  },
  data() {
    return {
      message: "",
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      center: [48.8534, 2.3488],
      zoom: 12,
      marker:[48.8534, 2.3488]
    };
  },
  created() {
    AXIOS.get("/geohashs")
      .then((response) => {
        this.message = response.data.data;
      })
      .catch((e) => {
        console.log(e);
      });
  },
  methods: {
    onReady(mapObject) {
      mapObject.locate();
    },
    onLocationFound(location) {
      this.center = [location.latitude, location.longitude]
      this.marker = [location.latitude, location.longitude]
    },
    zoomUpdated(zoom) {
      this.zoom = 30;
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
</style>

<template>
  <div>
    leaflet map component: {{this.message}}
    <l-map
        :center="center"
        :zoom="zoom"
        class="map"
        ref="map"
        @update:zoom="zoomUpdated"
        @update:center="centerUpdated"
    >
      <l-tile-layer
          :url="url"
      >
      </l-tile-layer>
    </l-map>
  </div>
</template>

<script>
import {AXIOS} from './http-common';
import 'leaflet/dist/leaflet.css';
import { LMap, LTileLayer } from 'vue2-leaflet';
export default {
  name: 'LeafletMap',
  components: {
    LMap,
    LTileLayer
  },
  data(){
    return {
      message:'',
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      center: [ 49.1193089, 6.1757156 ],
      zoom: 12,
    }
  },
  created() {
      AXIOS.get('/message')
          .then(response => {
            this.message = response.data.data
          })
          .catch(e => {
            console.log(e)
          })
    },
  methods: {
    zoomUpdated (zoom) {
      this.zoom = zoom;
      console.log(this.markers)
    },
    centerUpdated (center) {
      this.center = center;
    }
  }
}
</script>
<style>
.map {
  position: absolute;
  width: 100%;
  height: 100%;
  overflow :hidden
}
</style>
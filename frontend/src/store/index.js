import Vue from 'vue'
import Vuex from 'vuex'
import geohashs from './modules/geohashs'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    geohashs
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
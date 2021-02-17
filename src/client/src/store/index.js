import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id: localStorage.getItem("id") || null,
    username: localStorage.getItem("username") || null,
    game: null
  },
  mutations: {
    login(state, payload) {
        state.id = payload.id
        state.username = payload.username
    }
  },
  actions: {},
  modules: {}
})

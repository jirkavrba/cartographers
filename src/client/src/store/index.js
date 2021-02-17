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
  actions: {
    login(payload) {
      let id = (Math.random() * (1 << 16)).toString(36)
      let username = payload.username

      this.commit('login', {id, username})
    }
  },
  modules: {
  }
})

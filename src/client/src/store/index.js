import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        id: null,
        username: null,
        game: null
    },
    mutations: {
        login(state, payload) {
            state.id = payload.id
            state.username = payload.username
        },
        logout(state) {
            state.id = null;
            state.username = null;
        }
    },
    actions: {},
    modules: {}
})

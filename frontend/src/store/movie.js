import axios from 'axios'
import router from '../router'

const state = {
    context : 'http://localhost:5000/',
    naverMovie : [],
}

const actions = {
    async search({commit}, searchWord){
        alert('검색 : '+searchWord)
        axios.get(state.context+"movie/"+searchWord)
            .then(({data})=>{
                commit('SEARCH',data)
                router.push("/movie");
            })
            .catch(()=>{
                alert('실패!')
            })
    }

}

const mutations = {
    SEARCH(state,data){
        alert('mutation  영화 : '+data.count)
        state.naverMovie = []
        state.count = data.count
        data.list.forEach(item => {
            state.naverMovie.push({
                seq : item.seq,
                title : item.title
            })
        })

    }

}

const getters = {
    naverMovie : state => state.naverMovie,
    count : state => state.count

}

export default {
    name : "movie",
    namespaced : true,
    state,
    actions,
    mutations,
    getters

}
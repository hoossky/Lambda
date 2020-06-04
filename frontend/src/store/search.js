import router from "@/router"
import axios from "axios"

const state ={
    context : "http://localhost:5000",
    searchWord : 'null',
    pageNumber: '0',
    list : [],
    pages : [],
    pager: {},
    item : {}

}
const actions ={

    async find({commit},searchWord){
        alert('>>> '+searchWord)
        commit("SEARCHWORD",searchWord)
        switch (searchWord) {
            case '영화': router.push("/Movie")
                break
            case '음악': router.push("/Music")
                break
            case '축구': router.push("/Soccer")
                break
        }
    },
    async transferPage({commit},payload){
        alert(`${state.context}/${payload.category}/${payload.searchWord}/${payload.pageNumber}`)
        axios
            .get(`${state.context}/${payload.category}/${payload.searchWord}/${payload.pageNumber}`)
            .then(({data})=>{
                commit("TRANSFER",data)
            })
            .catch()
    },
    async retrieveOne({commit}, payload){
        axios
            .get(`${state.context}/${payload.category}/${payload.searchWord}`)
            .then(({data})=>{
                commit("DETAIL",data)
                router.push("/MovieDetail")
            })
            .catch()
    }


}
const mutations ={

    SEARCHWORD(state, data){
        state.searchWord = data
    },
    TRANSFER(state, data){
        alert("뮤테이션")
        state.pager = data.pager
        state.list = data.list
    },
    DETAIL(state, data){
        state.item = data

    }


}

export default {
    name:"search",
    namespaced: true,
    state,
    actions,
    mutations

}
import axios from 'axios'
import router from '../router'

const state = {
    context : 'http://localhost:5000/',
    bugsmusic : [],
    naverMovie : [],
    count : 0
}

const actions = {
    //'search'=>(){} Json'
    async search({commit}, searchWord){
        alert('검색어1 : '+searchWord)

        switch (searchWord) {
            case '벅스' :
                axios.post(state.context+`bugsmusic`,searchWord,{
                    authorization: 'JWT fefege..',
                    Accept : 'application/json',
                    'Content-Type': 'application/json'
                })
                    .then(({data})=>{
                        commit('SEARCH',data)
                        router.push("/retrieve")
                    })
                    .catch(()=>{
                        alert('통신 실패 !')
                    })
                    break

            case '네이버 영화' :
                alert("검색어2 : " + searchWord);
                axios
                    .get(state.context+`movie/list/0/${searchWord}`)
                    .then(({data})=>{
                        commit('MOVIE',data)
                        router.push("/movie")
                    })
                    .catch(()=>{
                        alert("영화 통신 실패!");
                    })
        }

    }
}

const mutations = {
    SEARCH(state, data) {
        alert('mutation 검색된 결과 수 : ' + data.count)
        state.bugsmusic = [] //new
        state.count = data.count
        data.list.forEach(item => {
            state.bugsmusic.push({
                seq: item.seq,
                artist: item.artist,
                title: item.title,
                album: item.album,
                thumbnail: item.thumbnail
            })
        })
    },
    MOVIE(state,data){
        alert('mutation movie : '+ data.count)
        state.naverMovie = []
        state.count = data.count
        data.list.forEach(item => {
            state.naverMovie.push({
                moveSeq : item.moveSeq,
                rank : item.rank,
                title : item.title,
                rankDate : item.rankDate
            })
        })
    }
}

const getters = {
    bugsmusic : state => state.bugsmusic, // 스테이트의 벅스뮤직을 리턴
    naverMovie : state => state.naverMovie,
    count : state => state.count
}

export default {
    name : 'crawling', //vuex
    namespaced : true,
    state,
    actions,
    mutations,
    getters
}
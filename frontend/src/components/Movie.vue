<template>
    <div>
        <img class="logo" src="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png">
        <span style="float:right; margin-right: 50px; margin-top: 50px"><input id="searchWord" type="text" @keyup.enter="search" class="search">
            <button @click="retrieve">검색</button>
            </span>
        <h3>총 게시글수 : {{pager.rowCount}}</h3>
        <br>
        <a @click="tester('aaaa')">테스트</a>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-left">No.</th>
                    <th class="text-left">순 위</th>
                    <th class="text-left">제 목</th>
                    <th class="text-left">집계일</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item of list" :key="item.movieSeq">
                    <td>{{item.movieSeq}}</td>
                    <td>{{item.rank}}</td>
                    <td><a @click="retrieveOne(item.movieSeq)" href="#">{{item.title}}</a></td>
                    <td>{{item.rankDate}}</td>
                </tr>
                </tbody>
            </template>
        </v-simple-table>
        <div class="text-center">
            <div style="margin: 0 auto; width: 250px; height: 100px"></div>
            <span @click="transferPage(pager.preBlock)" v-if="pager.existPrev" style="width: 50px; height: 50px; border: 1px solid #000000; margin: 5px">이 전</span>
            <span @click="transferPage(i-1)" v-for="i of pages" :key="i" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">
                {{i}}
            </span>
            <span @click="transferPage(pager.nextBlock)" v-if="pager.existNext" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">다 음</span>
            <!--<v-pagination v-model="page" :length="5"></v-pagination>-->

        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import {proxy} from "./mixins/proxy"
    export default {
        mixins : [proxy],
        data(){
            return {}
        },
        computed : {
            ...mapState({
                list : state => state.search.list,
                pages : state => state.search.pages,
                pager: state => state.search.pager
            })
        },
        created() {
            let json = proxy.methods.paging(`${this.$store.state.search.context}/movies/null/0`)
            this.$store.state.search.list = json.movies
            this.$store.state.search.pages = json.pages
            this.$store.state.search.pager = json.temp
        },
        methods:{
            transferPage(d){
                proxy.methods.tester(d)
                this.$store.dispatch('search/transferPage',{category : 'movies',
                                                                        searchWord : 'null',
                                                                        pageNumber : d}) //Number(d)
            },
            retrieve(){
                //proxy.methods.tester(document.getElementById('searchWord').value) // DOM처리 value() 함수처리
                let searchWord = document.getElementById('searchWord').value
                if(searchWord === '') searchWord = 'null'
                this.$store.dispatch('search/transferPage', {category : 'movies',
                                                                      searchWord : searchWord,
                                                                      pageNumber : 0})

            },
            retrieveOne(movieSeq){
                this.$store.dispatch('search/retrieveOne',{category:'movies' ,
                    searchWord:movieSeq})
            }
     }
    }
</script>

<style scoped>

    .search {border: 3px solid #f1f1f1}
    .logo{
        margin: 35px 50px;
        width: 100px;
        height: 40px;
    }
    /*.search {
        width: 40%;
        padding: 12px 20px;
        position: absolute;
        left: 200px;
        top: 30px;

        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }*/
    .count-text{
        position: absolute;
        left: 200px;
        top: 100px;
    }

</style>
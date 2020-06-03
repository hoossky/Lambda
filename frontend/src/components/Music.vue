<template>
    <div>
        <img class="logo" src="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png">
            <h4>검색 결과 : {{pager.rowCount}}</h4>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-left">No.</th>
                    <th class="text-left">제 목</th>
                    <th class="text-left">가 수</th>
                    <th class="text-left">앨 범</th>
                    <th class="text-left">이미지</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item of bugsmusic" :key="item.seq">
                    <td>{{ item.seq }}</td>
                    <td>{{ item.title }}</td>
                    <td>{{ item.artist }}</td>
                    <td>{{ item.album }}</td>
                    <td><img :src="item.thumbnail"></td>
                </tr>
                </tbody>
            </template>
        </v-simple-table>
        <div class="text-center">
            <div style="margin: 0 auto; width: 250px; height: 100px"></div>
            <span v-if="pager.existPrev" style="width: 50px; height: 50px; border: 1px solid #000000; margin: 5px">이 전</span>
            <span @click="transferPage(i)" v-for="i of pages" :key="i" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">
                {{i}}
            </span>
            <span v-if="pager.existNext" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">다 음</span>
            <!--<v-pagination v-model="page" :length="5"></v-pagination>-->

        </div>
       <!-- <div class="text-center">
            <v-pagination
                    v-model="page"
                    :length="5"
            ></v-pagination>
        </div>-->
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import axios from "axios";
    //import axios from "axios";
    export default {
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
            let json = paging(`${this.$store.state.search.context}/movies/null/0`)
            this.$store.state.search.list = json.movies
            this.$store.state.search.pages = json.pages
            this.$store.state.search.pager = json.temp
        },
        methods:{

            search(){
                alert('search')
                this.$store.dispatch('crawling/search',this.searchWord)


            }
        }

    }
    function paging(d) {
        const movies = []
        const pages = []
        let temp = {}

        axios
            .get(d)
            .then(({data}) => { //res (es5) = {data} (es6)
                data.list.forEach(elem => {
                    movies.push(elem)
                })
                console.log('페이징 메소드 : ' + data.pager.pageSize)
                let pager = data.pager
                alert(">>" + pager.rowCount)
                let i = pager.startPage + 1

                console.log(`페이지 끝 : ${pager.endPage + 1}`)

                for (; i <= pager.endPage + 1; i++) {
                    pages.push(i)
                }
                temp.rowCount = pager.rowCount
                temp.existPrev = pager.existPrev
                temp.existNext = pager.existNext

            })
            .catch(err => {
                alert(`영화 통신 실패! ${err}`)
            })

        return {movies, pages, temp}
    }
</script>

<style scoped>

    .search {border: 3px solid #f1f1f1}

    .logo{
        margin: 35px 50px;
        width: 100px;
        height: 40px;
    }

    .search {
        width: 40%;
        padding: 12px 20px;
        position: absolute;
        left: 200px;
        top: 30px;

        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .count-text{
        position: absolute;
        left: 200px;
        top: 100px;
    }
    .text-left{



    }


</style>
<template>
    <div>
        <img class="logo" src="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png">
        <br><br>
        <input type="text" @keyup.enter="search" v-model="searchWord" class="search" placeholder="검색된 결과">

        <div class="count-text">
            <h3>총 게시글수 : {{pager.rowCount}}</h3>
        </div>
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
                    <td>{{item.title}}</td>
                    <td>{{item.rankDate}}</td>
                </tr>
                </tbody>
            </template>
        </v-simple-table>
        <div class="text-center">
            <div style="margin: 0 auto; width: 250px; height: 100px"></div>
            <span v-if="pager.existPrev" style="width: 50px; height: 50px; border: 1px solid #000000; margin: 5px">이 전</span>
            <span v-for="i of pages" :key="i" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">
                {{i}}
            </span>
            <span v-if="pager.existNext" style="width: 50px; height: 50px; border: 1px solid black; margin: 5px">다 음</span>
            <!--<v-pagination v-model="page" :length="5"></v-pagination>-->

        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import axios from "axios";
    export default {

        data(){
            return {
                pageNumber: 0,
                existPrev : false,
                existNext : true,
                pages: [1,2,3,4,5],
                list: [],
                pager: {},
                totalCount: '',
                searchWord : ''

            }
        },
        computed : {
            ...mapState({

                count : state => state.crawling.count,
                naverMovie : state => state.crawling.naverMovie
            })
        },
        created() {
                axios
                    .get(`${this.$store.state.search.context}/movies/${this.$store.state.search.searchWord}/${this.$store.state.search.pageNumber}`)
                    .then(res =>{
                        res.data.list.forEach(elem => {this.list.push(elem)})
                        this.pager = res.data.pager
                        let i = this.pager.startPage + 1
                        let arr = []
                        console.log(`페이지 끝 : ${this.pager.endPage}`)

                        for(; i <= this.pager.endPage + 1; i++){
                            arr.push(i)
                        }
                        this.pages = arr

                    })
                    .catch(err=>{
                        alert(`영화 통신 실패! ${err}`)
                    })

        },
        methods:{

            search(){
                alert('search')
                this.$store.dispatch('crawling/search',this.searchWord)

            },

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

</style>
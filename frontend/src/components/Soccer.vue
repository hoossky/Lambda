<template>
    <div>
        <img class="logo" src="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png">
        <br><br>
        <input type="text" @keyup.enter="search" v-model="searchWord" class="search" placeholder="검색된 결과">

        <div class="count-text">
            <h4>검색 결과 : {{count}}</h4>
        </div>
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
            <v-pagination
                    v-model="page"
                    :length="5"
            ></v-pagination>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        data(){
            return {
                searchWord : '',
                page : 1

            }

        },
        computed : {
            ...mapState({

                count : state => state.crawling.count,
                bugsmusic: state => state.crawling.bugsmusic
            })
        },
        created() {
            alert("축구 진입")
        },
        methods:{

            search(){
                alert('search')
                this.$store.dispatch('crawling/search',this.searchWord)


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
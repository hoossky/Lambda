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
                    <th class="text-left">순 위</th>
                    <th class="text-left">제 목</th>

                </tr>
                </thead>
                <tbody>
                <tr v-for="item of naverMovie" :key="item.seq">
                    <td>{{ item.seq }}</td>
                    <td>{{ item.title }}</td>
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

                count : state => state.movie.count,
                naverMovie: state => state.movie.naverMovie

            })
        },
        methods:{

            search(){
                alert('search')
                this.$store.dispatch('movie/search',this.searchWord)


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

</style>
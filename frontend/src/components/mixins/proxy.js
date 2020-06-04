import axios from 'axios'

export const proxy = {
    methods: {
        tester(d) {
            alert(d)
            console.log(d)
        },
        paging(url){
            const movies = []
            const pages = []
            let temp = {}

                axios
                .get(url)
                .then(({data}) => {
                        data.list.forEach(elem => {movies.push(elem)})
                        let pager = data.pager
                        let i = pager.startPage + 1

                        for (; i <= pager.endPage + 1; i++) {
                            pages.push(i)
                        }
                        temp.rowCount = pager.rowCount
                        temp.existPrev = pager.existPrev
                        temp.existNext = pager.existNext
                        temp.nextBlock = pager.nextBlock
                        temp.preBlock = pager.preBlock
                        temp.nowPage = pager.nowPage

                    })
                    .catch(err => {
                        alert(`영화 통신 실패! ${err}`)
                    })

                return {movies, pages, temp}

            }
        }

}
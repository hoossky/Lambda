package com.lambda.web.music;

import com.lambda.web.mappers.MovieMapper;
import com.lambda.web.proxy.Box;
import com.lambda.web.proxy.IFunction;
import com.lambda.web.proxy.Pager;
import com.lambda.web.proxy.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
@RequestMapping("/movies")
public class MovieController{
    @Autowired Pager pager;
    @Autowired MovieMapper movieMapper;
    @Autowired Proxy pxy;
    @Autowired Box<Object> box;

    @GetMapping("/{searchWord}/{pageNumber}")
    public Map<?, ?> list(@PathVariable("pageNumber") String pageNumber,
                                    @PathVariable("searchWord") String searchWord){

        if(searchWord.equals("null")){
            pxy.print("검색어가 없음");
            pager.setSearchWord("");
        }else{
            pxy.print("검색어3 : "+searchWord);
            pager.setSearchWord(searchWord);
        }

        pxy.print("넘어온 페이지 번호 : " + pageNumber);

        pager.setNowPage(pxy.integer(pageNumber))
        ;
        pager.setBlockSize(5);
        pager.setPageSize(5);

        pager.paging();
        IFunction<Pager, List<MovieDTO>> f = p -> movieMapper.selectMovies(p);
        List<MovieDTO> l = f.apply(pager);
        pxy.print("------------");
        for(MovieDTO m : l){
            pxy.print(m.toString());
        }
        box.clear();
        box.put("pager", pager);
        box.put("list", l);

        return box.get();
    }
    @GetMapping("/{searchWord}")
    public MovieDTO detail(@PathVariable("searchWord") String searchWord){
        return movieMapper.selectMovie(searchWord);
    }

}
package com.lambda.web.music;

import com.lambda.web.mappers.MovieMapper;
import com.lambda.web.proxy.Box;
import com.lambda.web.proxy.IFunction;
import com.lambda.web.proxy.Pager;
import com.lambda.web.proxy.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
@RequestMapping("/movie")
public class MovieController{
    @Autowired Pager pager;
    @Autowired MovieMapper movieMapper;
    @Autowired Proxy pxy;
    @Autowired Box<Object> box;

    @GetMapping("/list/{pageNumber}/{searchWord}")
    public Map<?, ?> list(@PathVariable("pageNumber") String pageNumber,
                                    @PathVariable("searchWord") String searchWord){

        if(searchWord.equals("")){
            pxy.print("검색어가 없음");
        }else{
            pxy.print("검색어3 : "+searchWord);
        }

        pager.setNowPage(pxy.integer(pageNumber));
        pager.setBlockSize(5);
        pager.setPageSize(5);
        pager.paging();
        IFunction<Pager, List<MovieDTO>> f = p -> movieMapper.selectMovies(p);
        List<MovieDTO> l = f.apply(pager);
        pxy.print("444444444444");
        for(MovieDTO m : l){
            pxy.print(m.toString());
        }
        box.clear();
        box.put("count", pager);
        box.put("list", l);

        return box.get();
    }
}
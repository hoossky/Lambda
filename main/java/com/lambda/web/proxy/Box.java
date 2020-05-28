package com.lambda.web.proxy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.function.Function;

@Component @Lazy
public class Box<T> {
    private HashMap<String, T> box;
    public Box(){box = new HashMap<>();}
    // add, get(k), get(map), clear
    public void put(String s, T t){box.put(s,t);}
    public T get(String k){
        Function<String ,T> s = p->box.get(p);
        return s.apply(k);
    }
    public HashMap<String, T> get(){return box;}
    /*public int size(){
        Supplier<Integer> f = box ::size
        return f.get()

    }*/
    public int size(){return box.size();}
    public void clear(){box.clear();}
    public void newBox(){box = new HashMap<String, T>();}
}

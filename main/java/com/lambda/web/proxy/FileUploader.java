package com.lambda.web.proxy;

import com.lambda.web.soccer.Player;
import com.lambda.web.soccer.PlayerDTO;
import com.lambda.web.soccer.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service("uploader") @Lazy
public class FileUploader extends Proxy{
    @Autowired Inventory<String> inventory;
    @Autowired PlayerRepository playerRepository;

    public void upload(){
        inventory.clear();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\bit\\IdeaProjects\\lambda\\src\\main\\resources\\static\\files\\player.csv")));
            String player = "";
            while((player = reader.readLine()) != null){
                inventory.add(player);
            }
        }catch(Exception e){
            print("파일 리딩 에러");
            e.printStackTrace();
        }
        print("--------------------------------------\n");
        print(inventory.get().get(0).toString());
        String[] arr = inventory.get().get(0).toString().split(",");
        // "2000003","유동우","K10","YOU,  DONGWOO","","","DF","40","","07-MAR-1978","1","177","70"
        Player player = new Player();
        player.setPlayerId(arr[0]);
        player.setPlayerName(arr[1]);
        player.setEPlayerName(arr[3]);
        player.setNickname(arr[4]);
        player.setJoinYyyy(arr[5]);
        player.setPosition(arr[6]);
        player.setBackNo(arr[7]);
        player.setNation(arr[8]);
        player.setBirthDate(arr[9]);
        player.setSolar(arr[10]);
        player.setHeight(arr[11]);
        player.setWeight(arr[12]);
        print("--------------------------------------\n");
        print(player.toString());
        // p.setTeam();
        playerRepository.save(player);

    }
}
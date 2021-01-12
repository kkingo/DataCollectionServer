package com.datacollcet.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datacollcet.dao.BeaconAP;
import com.datacollcet.dao.V2iDataMapper;
import com.datacollcet.pojo.*;
import com.datacollcet.service.V2iService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/V2I")
public class CollectV2iController {
    private static final String PY_URL =  "F:\\python_code_repo\\backend_process.py";
    private String url = "http://yingyan.baidu.com/api/v3/track/addpoint";
    private String ak = "m3ENP7azbreSpGQDAqtrj1QuSrUqKjTv";
    private int service_id = 162210;

    //其他行业的服务
//    private String ak = "m3ENP7azbreSpGQDAqtrj1QuSrUqKjTv";
   // private int service_id = 117286;

//    private String entity_name = "Car0";


    @Autowired
    V2iService v2iService;
    @Autowired
    V2iDataMapper mapperdao;

    @RequestMapping("fpsCollection")
    @ResponseBody
    public String fpsCollection(@RequestBody String jsonString) throws InterruptedException {
        Gson gson = new Gson();
        List<NNfingerprint> fps = gson.fromJson(jsonString, new TypeToken<List<NNfingerprint>>() {
        }.getType());
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        int insertAmount = 0;
        for (int i = 0; i < fps.size(); i++) {
            System.out.println(fps.get(i).toString());
            insertAmount += mapperdao.insertNNFingerprint(fps.get(i));
            }
        System.out.println("collected "+insertAmount+
                " fingerprints\n at "+ fps.get(0).getRpid()+" is successfully stored" +
                "\ncurrent time:"+df.format(System.currentTimeMillis()));
        System.out.println("************************");
        return String.valueOf(insertAmount);
    }

}

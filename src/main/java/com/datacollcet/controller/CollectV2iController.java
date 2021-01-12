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


//
    @RequestMapping("qrcode")
    @ResponseBody
    public String qrcode(@RequestBody String jsonInfo) throws InterruptedException {
        System.out.println(jsonInfo);
        return "success";
    }

    @RequestMapping("getcontrol")
    @ResponseBody
    public String getcontrol(@RequestBody String jsonInfo) throws InterruptedException {
        System.out.println(jsonInfo);
        return "success";
    }

    @RequestMapping("uploadState")
    @ResponseBody
    public String uploatState(@RequestBody String jsonInfo) throws InterruptedException {
        System.out.println(jsonInfo);
        return "success";
    }

    @RequestMapping("uploadImage")
    @ResponseBody
    public String uploadimage(@RequestBody String jsonInfo) throws InterruptedException {
        System.out.println(jsonInfo);
        return "success";
    }

    @RequestMapping("FingerprintCrowdsourcing")
    @ResponseBody
    public String fpCollect(@RequestBody String jsonInfo) throws InterruptedException {
    int insertAmount = 0;
    Gson gson = new Gson();
    List<NNfingerprint> fingerprints = gson.fromJson(jsonInfo, new TypeToken<List<NNfingerprint>>() {
    }.getType());
    System.out.println("receive client info:" + fingerprints.size()+"fingerprints");
    for (NNfingerprint fp: fingerprints) {
        insertAmount += mapperdao.insertNNFingerprint(fp);
    }
    System.out.println(insertAmount);
    return String.valueOf(fingerprints.size()-insertAmount);
}

    @RequestMapping("DrivingTrajCollection")
    @ResponseBody
    public String trajCollection(@RequestBody String info) throws InterruptedException {
        Gson gson = new Gson();
        List<TrajInfo> Infos = gson.fromJson(info, new TypeToken<List<TrajInfo>>() {}.getType());
        System.out.println("receive client info:" + Infos.size()+"times data");
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        int insertAmount = 0;
        for (int i = 0; i < Infos.size(); i++) {
            insertAmount += mapperdao.insertDrivingTraj(Infos.get(i));
        }
        System.out.println(" The device :"+Infos.get(0).getMacAddr()+"\ncollected " +Infos.get(0).getTrajID()+" trajectory is successfully stored" + "\ncurrent time:"+df.format(System.currentTimeMillis()));
        System.out.println("************************");
        if(insertAmount == Infos.size())
            return "success";
        return String.valueOf(insertAmount);
    }

//  接收离线收集的WiFi信息，用于训练神经网络
    @RequestMapping("FingerprintCollection")
    @ResponseBody
    public String fpCollection(@RequestBody String info) throws InterruptedException {
    Gson gson = new Gson();
    WiFiInfo wifiInfo = gson.fromJson(info, WiFiInfo.class);
    System.out.println("receive client info:" + wifiInfo.getWiFiFeatures().size()+"fingerprints");
    SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    int insertAmount = 0;
    for (int i = 0; i < wifiInfo.getWiFiFeatures().size(); i++) {
        NNfingerprint nfingerprint = new NNfingerprint();
        nfingerprint.setRpid(wifiInfo.getRpid());
        nfingerprint.setSubid(wifiInfo.getSubid());
        nfingerprint.setWiFiFeatures(gson.toJson(fromMaptoList(wifiInfo.getWiFiFeatures().get(i))));
        nfingerprint.setXaxis(wifiInfo.getXaxis());
        nfingerprint.setYaxis(wifiInfo.getYaxis());
        nfingerprint.setTimeStamp(df.format(wifiInfo.getTimeStamp() - (wifiInfo.getWiFiFeatures().size()-i)*wifiInfo.getSampleingIntervel()));
        nfingerprint.setMAC(wifiInfo.getMacAddr());
        if(i < wifiInfo.getMagReads().size()){
            nfingerprint.setMagReading(gson.toJson(wifiInfo.getMagReads().get(i)));
        }
        insertAmount += mapperdao.insertNNFingerprint(nfingerprint);
    }
    System.out.println(" The device :"+wifiInfo.getMacAddr()+"\ncollected "+insertAmount+
            " fingerprints\n at "+ wifiInfo.getRpid()+" is successfully stored" + "\ncurrent time:"+df.format(System.currentTimeMillis()));
    System.out.println("************************");
    if(insertAmount == wifiInfo.getWiFiFeatures().size())
        return "success";
    return String.valueOf(insertAmount);
}

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


//  接收用户发送的fingerprint
    @RequestMapping("/fingerprint")
    @ResponseBody
    public String fingerprint(@RequestBody FingerPrint[] fingerprints){
        System.out.println("the number of received fingerprint is " + fingerprints.length);
        for (FingerPrint fingerprint: fingerprints) {
            System.out.println(fingerprint.toString());
            mapperdao.insertFingerprint(fingerprint);
        }
        if(execPy())
            return "successfully stored " + String.valueOf(fingerprints.length)+" fingerprints";
        return "fail to  store fingerprint, please upload again";
    }

    @RequestMapping("/matchedTrajs")
    @ResponseBody
    public String fingerprint(@RequestBody Position[] positions){
        System.out.println("received array length is " + positions.length);
        for (Position pos: positions) {
            mapperdao.insertMatchedTraj(pos);
        }
        return "fail to  store fingerprint, please upload again";
    }


//  接收用户扫描的在线指纹，执行knn定位
    @RequestMapping("/kNNlocalization")
    @ResponseBody
    public String  kNNLocalization(@RequestBody String fingerprint){
        String sendJson = "";
        Gson gson = new Gson();
        System.out.println("received fingeprirnt is " + fingerprint);
        List<FingerPrint> fingerPrints = mapperdao.getFingerprint();
        Map<String, Integer> onlinefp = gson.fromJson(fingerprint, new TypeToken<Map<String, Integer>>() {
        }.getType());
        if(!fingerPrints.isEmpty()){
            FingerPrint offineFP = knnLocalization(onlinefp,fingerPrints);
            sendJson =  gson.toJson(offineFP);
            System.out.println(sendJson);
        }
        return sendJson;
    }

//  接收用户扫描的在线指纹，执行我们提出的定位算法
    @RequestMapping("/Mylocalization")
    @ResponseBody
    public String  MyLocalization(@RequestBody String fingerprint){
        Gson gson = new Gson();
        System.out.println("received fingeprirnt is " + fingerprint);
        List<MyFingerPrint> mFingerPrints = mapperdao.getRefinedFingerprint();
        Map<String, Integer> onlinefp = gson.fromJson(fingerprint, new TypeToken<Map<String, Integer>>() {
        }.getType());
        List<Subarea> subareas = mapperdao.getSubarea();
        LocalizationResult localizationResult = newLocalization(onlinefp,mFingerPrints,subareas);
        String sendJson =  gson.toJson(localizationResult);
        System.out.println(sendJson);
        return sendJson;
    }

//  接收实验测试的定位结果，用于计算定位误差
    @RequestMapping("/testposition")
    @ResponseBody
    public String position(@RequestBody Position[] positions){
        System.out.println(positions);
        System.out.println("received position length is " + positions.length);
        for (Position position: positions) {
            mapperdao.insertPosition(position);
        }
        return "successfully stored " + String.valueOf(positions)+" positions ";
    }

    @RequestMapping("/receiveposition")
    @ResponseBody
    public String recordPosition(@RequestBody String positionStr){
        System.out.println("receive positions");
        Gson gson = new Gson();
        Position position = gson.fromJson(positionStr, Position.class);
        System.out.println(position.toString());
        mapperdao.insertPosition(position);
        return "successfully stored " + String.valueOf(positionStr)+" positions ";
    }

//  获取数据库中所有的fingerprint
    @RequestMapping("/getfingerprint")
    @ResponseBody
    public String getfingerprint(){
        List<FingerPrint> fingerprints = mapperdao.getFingerprint();
        Gson gson = new Gson();
        System.out.println(gson.toJson(fingerprints));
        return gson.toJson(fingerprints);
    }

    @RequestMapping("/getGmap")
    @ResponseBody
    public String getGmap(){
        List<GradientFP> gmap = mapperdao.getGmap();
        Gson gson = new Gson();
        System.out.println(gson.toJson(gmap));
        return gson.toJson(gmap);
    }


//  获取数据库中所有的position
    @RequestMapping("/getposition")
    @ResponseBody
    public String getposition(){
        System.out.println("receive position request");
        double length = 32.51455;
        double width = 24;
        List<Position> convertedPos = new ArrayList<>();
        List<Position> positions = mapperdao.getPosition();
//        for (Position position: positions) {
//            position.setXaxis((length-(position.getXaxis()/100))*19.5);
//            position.setYaxis((width-(position.getYaxis()/100))*19.5);
//        }
        for (Position position: positions) {
            position.setXaxis(position.getXaxis());
            position.setYaxis(position.getYaxis());
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(positions));
        return gson.toJson(positions);
    }

//  获取数据库中所有的subarea
    @RequestMapping("/getsubarea")
    @ResponseBody
    public String getSubarea(){
        System.out.println("client request for subarea info");
        List<Subarea> subareas = mapperdao.getSubarea();
        Gson gson = new Gson();
        System.out.println(gson.toJson(subareas));
        return gson.toJson(subareas);
    }

//  接收用户手机传感器的读数
    @RequestMapping("/Readings")
    @ResponseBody
    public String Readings(@RequestBody Readings[] readings){
        int length = 0;
        System.out.println("received readings length is " + readings.length);
        for(Readings reading : readings){
            length = mapperdao.insertReadings(reading);
        }
        System.out.println("insert length"+String.valueOf(length));
        return "insert length"+String.valueOf(length);
    }


    @RequestMapping("/convergences")
    @ResponseBody
    public String convergence(@RequestBody String jsonList){
        Gson gson = new Gson();
        List<Double> convergences = gson.fromJson(jsonList, new TypeToken<List<Double>>(){}.getType());
        for(Double convergence: convergences)
            mapperdao.insertConvergence(convergence);
        return "insert length"+String.valueOf(convergences.size());
    }


    @RequestMapping("/MagReadings")
    @ResponseBody
    public String mReadings(@RequestBody String jsondata){
        Gson gson = new Gson();
        List<MagReadings> readings = gson.fromJson(jsondata, new TypeToken<List<MagReadings>>(){}.getType());
        int length = 0;
        System.out.println("received readings length is " + readings.size());
        for(MagReadings reading : readings){
             int id = reading.getId();
             List<Float> mags = reading.getMags();
             for(Float f: mags){
                 mapperdao.insertMagReadings(new Mag(id, f));
             }
        }
        System.out.println("insert length"+String.valueOf(length));
        return "insert length"+String.valueOf(length);
    }

//  用于收集数据的App的链接测试
    @RequestMapping("/recordnumbers")
    @ResponseBody
    public String recordnumbers(@RequestBody RecordNumber[] numbers){
        System.out.println(String.valueOf(numbers.length));
        for(RecordNumber number: numbers){
            mapperdao.insertNumbers(number);
        }
        return String.valueOf(numbers.length);
    }


    @RequestMapping("/recordsteps")
    @ResponseBody
    public String recordsteps(@RequestBody Steps[] steps){
        System.out.println(String.valueOf(steps.length));
        for(Steps step: steps){
            mapperdao.insertStep(step);
        }
        return String.valueOf(steps.length);
    }

    //  用于收集数据的App的链接测试
    @RequestMapping("/linktest")
    @ResponseBody
    public String test(){
        return "true";
    }

//  用于接收离线收集的数据
    @RequestMapping("/offlinedata")
    @ResponseBody
    public String offlinedata(@RequestBody String jsonString){
        Gson gson = new Gson();
        List<OfflineData> datas = gson.fromJson(jsonString, new TypeToken<List<OfflineData>>(){}.getType());
        for(OfflineData data : datas){
            System.out.println(data.toString());
        }
        return "insert length"+String.valueOf(datas.size());
    }

//  发送地图信息
    @RequestMapping("/pixelmap")
    @ResponseBody
    public String PixelMap(){
        System.out.println("receive client request at pixelmap");
        Gson gson = new Gson();
        List<PixelMap> pixelMaps = mapperdao.getPixelMap();
        PixelMap lastElment = pixelMaps.get(pixelMaps.size()-1);
        System.out.println(lastElment.toString());
        int[][] pixelmaps = new int[lastElment.getXaxis()+1][lastElment.getYaxis()+1];
        for(PixelMap pixel: pixelMaps){
            if(pixel.getPixel() == 1)
                pixelmaps[pixel.getXaxis()][pixel.getYaxis()] = 1;
            else {
                pixelmaps[pixel.getXaxis()][pixel.getYaxis()] = 0;
            }
        }
        return gson.toJson(pixelmaps);
    }


    //  发送地图信息
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody LocalizationResult result){
        Map<Integer, Double> relatives = result.getRelatives();
        for(Map.Entry<Integer, Double> re : relatives.entrySet()){
            mapperdao.updateFingerprint(new Relative(re.getKey(), re.getValue()));
        }
        return String.valueOf(relatives.size());
    }

//  knn具体的算法实现
    public FingerPrint knnLocalization(Map<String,Integer> onlinefp, List<FingerPrint> fingerPrints){
        Gson gson = new Gson();
        Map<Double, FingerPrint> distances = new TreeMap<>();
        for(FingerPrint fingerPrint: fingerPrints){
                int rpid = fingerPrint.getId();
                String fingerprint = fingerPrint.getRssi();
                Map<String, Integer> fingerprintMap = gson.fromJson(fingerprint, new TypeToken<Map<String, Integer>>() {
                }.getType());
                double xaxis = fingerPrint.getXaxis();
                double yaxis = fingerPrint.getYaxis();
                double distance = 0.0;
                for(Map.Entry<String,Integer> macpair : onlinefp.entrySet()){
                    if(fingerprintMap.containsKey(macpair.getKey())){
                        int rss = fingerprintMap.get(macpair.getKey());
                        distance = distance + Math.pow((rss - macpair.getValue()), 2);
                    }
                }
                distances.put(Math.sqrt(distance), new FingerPrint(rpid, fingerprint, xaxis, yaxis));
            }
        return distances.get(Collections.min(distances.keySet()));
    }

//  我们提出的算法的具体实现
    private LocalizationResult newLocalization(Map<String,Integer> onlinefp, List<MyFingerPrint> mFingerPrints, List<Subarea> subareas) {
        Gson gson = new Gson();
        Map<Double, MyFingerPrint> distances = new TreeMap<>();
        Subarea targetSubarea = null;
        Map<Integer, Double> relatives = new HashMap<>();
        for(MyFingerPrint fingerPrint: mFingerPrints){
            String fingerprint = fingerPrint.getRssi();
            Map<String, Integer> fingerprintMap = gson.fromJson(fingerprint, new TypeToken<Map<String, Integer>>() {
            }.getType());
            double distance = 0.0;
            for(Map.Entry<String,Integer> macpair : onlinefp.entrySet()){
                if(fingerprintMap.containsKey(macpair.getKey())){
                    int rss = fingerprintMap.get(macpair.getKey());
                    distance = distance + Math.pow((rss - macpair.getValue()), 2);
                }
            }
            distances.put(Math.sqrt(distance), fingerPrint);
        }
        int k = 0;
        int selectRPs = 6;
        double[] votes = new double[subareas.size()];
        Arrays.fill(votes, 0 );
        for (Map.Entry<Double, MyFingerPrint> neighbor: distances.entrySet()) {
            double distance = neighbor.getKey();
            MyFingerPrint fingerPrint = neighbor.getValue();
            relatives.put(neighbor.getValue().getId(), neighbor.getValue().getRank());
            Double vote = votes[fingerPrint.getSubarea()];
            votes[fingerPrint.getSubarea()] = vote +fingerPrint.getRank();
            k++;
            if(k == selectRPs)
                break;
        }
        int maxIndex = -1;
        double maxValue = 0.0;
        for (int i = 0; i < votes.length; i++) {
            if(maxValue < votes[i]){
                maxValue = votes[i];
                maxIndex = i;
            }
        }

        for(Subarea subarea: subareas){
            if(subarea.getSbid()== maxIndex)
                targetSubarea = subarea;
        }

        for(Map.Entry<Integer, Double> relative: relatives.entrySet()){
            if(!relative.getKey().equals(targetSubarea))
                relatives.remove(relative.getKey());
        }
        return new LocalizationResult(relatives, targetSubarea);
    }

//  执行python脚本程序
    public Boolean execPy() {
        Boolean isSucceessfulExected = false;
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("python " + PY_URL);
            int result = proc.waitFor();
            if(result == 0)
                isSucceessfulExected = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isSucceessfulExected;
    }

    public List fromMaptoList(Map<String, Integer> map){
        List<Integer> rssi = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            rssi.add(entry.getValue());
        }
        return rssi;
    }
}

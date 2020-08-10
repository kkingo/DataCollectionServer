package com.datacollcet;


import com.datacollcet.dao.V2iDataMapper;
import com.datacollcet.pojo.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.datacollcet.dao")
public class TestEnum {
    @Autowired
    V2iDataMapper mapperdao;
    static final int TIME_WINDOW =7;
    static final double TTIME_DELAY = 0.02;
    static final double MIN_TIME_INTERVAL = 0.2;
    static final double MAX_TIME_INTERVAL = 2;
    private double MaxT = 3.5;
    private double MinT = -1;
    static final float THREAD_VALUE = (float)0.8;
    static final float INITIAL_VALUE = (float)0.5;
    private Queue<Double> surroundValues = new LinkedList<>();
    private int now = 0;
    private double valueOfPeak = 0;
    private double valueOfValley = 0;
    private double timeOfNow;
    private double timeOfLastValley;
    private List<Double> valleyValues = new ArrayList<>();



    @Test
    public void test(){
        System.out.println(truncation(3.1415f));
    }

    private float truncation(float a){
        float a1 = ((float)Math.round(a*1000))/1000;
        return a1;
    }
    private void newStepDetection(double readings) {
        if(surroundValues.size() < TIME_WINDOW){
            surroundValues.offer(readings);

        }else {
            surroundValues.poll();
            surroundValues.offer(readings);
            int result =valleyPeakDetectionNew();
            if(result == 1){
//                System.out.println("current time is " +String.valueOf((now-3)*TTIME_DELAY)+" peak value is "+ String.valueOf(valueOfPeak));
            }
            if(result == -1){
                timeOfNow = (now-3)*TTIME_DELAY;
                if(timeOfNow - timeOfLastValley >= MIN_TIME_INTERVAL
                        && (valueOfPeak - valueOfValley >= THREAD_VALUE)
                        && (timeOfNow - timeOfLastValley) <= MAX_TIME_INTERVAL){
                    timeOfLastValley = timeOfNow;
                    System.out.println("new step detected at "+String.valueOf((now-3)*TTIME_DELAY)+" and valley value is "+String.valueOf(valueOfValley));
                    valleyValues.add(valueOfValley);
                    System.out.println("uppper bound of valley value is" +String.valueOf(Collections.max(valleyValues)));
                    System.out.println("lower bound of valley value is" +String.valueOf(Collections.min(valleyValues)));
                }
                if (timeOfNow - timeOfLastValley >= MIN_TIME_INTERVAL
                        && (valueOfPeak - valueOfValley >= INITIAL_VALUE)) {
                    timeOfLastValley = timeOfNow;
                }
            }
        }
    }

    private void valleyPeakDetection() {
        Double[][] halfs = splitToArray(surroundValues);
        int formerTrend = trendRecognition(halfs[0]);
        int laterTrend = trendRecognition(halfs[1]);
        if(formerTrend == -1 && laterTrend == -1)
            System.out.println(String.valueOf((now-6)*TTIME_DELAY)+"-"+String.valueOf(now*TTIME_DELAY)+" :valley detected");
        if(formerTrend == 1  && laterTrend == 1)
            System.out.println(String.valueOf((now-6)*TTIME_DELAY)+"-"+String.valueOf(now*TTIME_DELAY)+" :peak detected");
        if(formerTrend == -1 && laterTrend == 1)
            System.out.println(String.valueOf((now-6)*TTIME_DELAY)+"-"+String.valueOf(now*TTIME_DELAY)+" :continue dropping");
        if(formerTrend == 1 && laterTrend == -1)
            System.out.println(String.valueOf((now-6)*TTIME_DELAY)+"-"+String.valueOf(now*TTIME_DELAY)+" :continue increasing");
    }

    private int trendRecognition(Double[] half) {
        int trend = 0;   //0代表无明显趋势，1代表上升， -1代表下降
        int isAscending = 0;
        int isDescending = 0;
        for(int i = 1; i <half.length; i++){
            if (half[i] > half[i-1] && Math.abs(half[i] - half[i-1]) > 0.01){
                isAscending++;
            }
            if(half[i] < half[i-1] && Math.abs(half[i] - half[i-1]) > 0.01){
                isDescending++;
            }
        }
        if(isAscending == half.length - 1)
            return 1;
        if(isDescending == half.length - 1)
            return -1;
        return 0;
    }

    private Double[][] splitToArray(Queue<Double> queue){
        Double[] array = new Double[queue.size()];
        int i = 0;
        Double[][]  halfs = new Double[2][(TIME_WINDOW-1)/2]; //half[0]为前半段, half[1] 为后半段且顺序与原来相反
        for (Double value: queue) {
            if(i<(TIME_WINDOW-1)/2){
                halfs[0][i] = value;
            }
            if(i>(TIME_WINDOW-1)/2){
                halfs[1][TIME_WINDOW-i-1] = value; //相反的顺序
            }
            i++;
        }
        return halfs;
    }

    private int valleyPeakDetectionNew(){
        int result = 0; //结果为1为peak,-1为valley
        int i = 0;
        double[] array = new double[surroundValues.size()];
        for(Double value: surroundValues) {
            array[i] = value;
            i++;
        }

        if(array[(TIME_WINDOW-1)/2] == Collections.min(surroundValues)){
            if(array[(TIME_WINDOW-1)/2] > MinT && array[(TIME_WINDOW-1)/2] < MaxT){
                result = -1;
                valueOfValley = array[(TIME_WINDOW-1)/2];
            }
        }
        if(array[(TIME_WINDOW-1)/2] == Collections.max(surroundValues)){
            result = 1;
            valueOfPeak = array[(TIME_WINDOW-1)/2];
        }
        return result;
    }
}


//    /**
//     * 循环枚举,输出ordinal属性；若枚举有内部属性，则也输出。(说的就是我定义的TYPE类型的枚举的typeName属性)
//     */
//    private static void forEnum() {
//        for (SimpleEnum simpleEnum : SimpleEnum.values()) {
//            System.out.println(simpleEnum + "  ordinal  " + simpleEnum.ordinal());
//        }
//        System.out.println("------------------");
//        for (TYPE type : TYPE.values()) {
//            System.out.println("type = " + type + "    type.name = " + type.name() + "   typeName = " + type.getTypeName() + "   ordinal = " + type.ordinal());
//        }
//    }
//
//    /**
//     * 在Java代码使用枚举
//     */
//    private static void useEnumInJava() {
//        String typeName = "f5";
//        TYPE type = TYPE.fromTypeName(typeName);
//        if (TYPE.BALANCE.equals(type)) {
//            System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");
//        } else {
//            System.out.println("大师兄代码错误");
//        }
//
//    }
//
//    /**
//     * 季节枚举(不带参数的枚举常量)这个是最简单的枚举使用实例
//     * Ordinal 属性，对应的就是排列顺序，从0开始。
//     */
//    private enum SimpleEnum {
//        SPRING,
//        SUMMER,
//        AUTUMN,
//        WINTER
//    }
//
//
//    /**
//     * 常用类型(带参数的枚举常量，这个只是在书上不常见，实际使用还是很多的，看懂这个，使用就不是问题啦。)
//     */
//    private enum TYPE {
//        FIREWALL("firewall"),
//        SECRET("secretMac"),
//        BALANCE("f5");
//
//        private String typeName;
//
//        TYPE(String typeName) {
//            this.typeName = typeName;
//        }
//
//        /**
//         * 根据类型的名称，返回类型的枚举实例。
//         *
//         * @param typeName 类型名称
//         */
//        public static TYPE fromTypeName(String typeName) {
//            for (TYPE type : TYPE.values()) {
//                if (type.getTypeName().equals(typeName)) {
//                    return type;
//                }
//            }
//            return null;
//        }
//
//        public String getTypeName() {
//            return this.typeName;
//        }
//    }
//
//    @Test
//    public void testEncode(){
//        SimpleDateFormat df = new SimpleDateFormat();
//        System.out.println(df.format(System.currentTimeMillis()));
//    }



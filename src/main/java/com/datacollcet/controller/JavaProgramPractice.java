package com.datacollcet.controller;

import java.util.Arrays;
import java.util.Scanner;

public class JavaProgramPractice {

    //1.写一个函数， 求一个字符串的长度， 从键盘中输入字符串， 并输出其长度
    static void sortThreeNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = input.nextLine();
        System.out.println("该字符串的长度是："+str.length());
    }

    //2.统计101到200间有多少个素数
    static void countPrimeNumber(){
        int count = 0;
        for(int i = 101; i <= 200; i++)
        {
            boolean b = false;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if(i%j == 0)
                {
                    b = false;
                    break;
                }
                else
                {
                    b = true;
                }
            }
            if(b == true)
            {
                count ++;
                System.out.println(i);
            }
        }
        System.out.println("素数的总数为："+count);
    }

    //3.输入某年某月某日，判断这一天是这一年的第几天？
    static void testDay(){
        Scanner input = new Scanner(System.in);
        int day , month ,year , dayNum = 0;//定义年月日，以及本月之前的总天数
        while(true)
        {
            System.out.println("请输入年：");
            year = input.nextInt();
            System.out.println("请输入月：");
            month = input.nextInt();
            System.out.println("请输入日：");
            day = input.nextInt();
            if(month < 1 || month > 12 || day < 1 || day > 31)
                continue;
            else
                break;
        }
        for(int i =1; i < month; i++)//通过循环来找到本月之前的总天数；判断月的总天数和闰年等
        {
            int days = 0;
            switch(i)
            {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;
                    break;
                case 2://闰年29天，非闰年28天
                    if(year % 400 ==0||(year%4 == 0 && year % 100 != 0))
                        days = 29;
                    else
                        days = 28;
                    break;
            }
            dayNum += days;//输入月份之前月份的总天数
        }
        System.out.println("这是本年的第"+(dayNum+day)+"天");
    }

    //4.输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数
    static void countCharacter(){
        System.out.println("请输入一行字符串：");
        String str = new Scanner(System.in).nextLine();
        int digital = 0,character = 0, other = 0, blank = 0;
        char [] ch = str.toCharArray();//String的方法，将字符串转换为字符数组；
        for (int i = 0; i <ch.length; i++) {
            if(ch[i] >= 'a' && ch[i] <= 'z' || ch[i] >= 'A' && ch[i] <= 'Z')
                character++;
            else if(ch[i] >= '0' && ch[i] <= '9')
                digital++;
            else if(ch[i] == ' ')
                blank++;
            else
                other++;
        }
        System.out.println("字母个数："+character);
        System.out.println("数字个数："+digital);
        System.out.println("空格个数："+blank);
        System.out.println("其他个数："+other);
    }

    //5.一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如 6=1＋2＋3.编程找出 1000 以内的所有完数
    static void findPerfectNumber(){
        for (int i = 1; i <= 1000; i++) {
            int b = 0;//每次都要把b重置
            for (int j = 1; j <= i/2; j++) {
                if(i % j == 0)//找到因数，然后相加
                {
                    b = b + j;//相加供后边使用
                }
            }
            if(i == b)//如果是完数，则输出完数
                System.out.println(i);
        }
    }

    //6.一球从 100 米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在第10 次落地时，共经过多少米？第 10 次反弹多高
    static void calculateBounceHeight(){
        int num = 10; //弹跳次数
        double sum = 0, high = 100;
        for (int i = 1; i < num; i++) {
            if(i == 1)
                sum += high;
            else
                sum = sum + 2*high;
            if(i < 10)
                high /= 2;
        }
        System.out.println("第"+num+"次时经过"+sum+"米，第"+num+"次反弹"+high+"米！");
    }

    //7.有 1、 2、 3、 4 四个数字， 能组成多少个互不相同且无重复数字的三位数？都是多少？
    static void unrepeatedNumber(){
        int count = 0 ;
        for (int i = 1; i < 5; i++) {//最外层循环，控制百位数；
            for (int j = 1; j < 5; j++) {//第二层循环控制十位数；
                for (int z =1; z < 5; z++) {//第三层循环控制个位数；
                    if(i!=j&&i!=z&&j!=z)//如果三个位上的值互不相等，执行计数操作；
                    {
                        count++;
                        System.out.println(i*100+j*10+z);
                    }
                }
            }
        }
        System.out.println("共有"+count+"个这样的数！");
    }

    //8.输入数组， 最大的与第一个元素交换， 最小的与最后一个元素交换， 输出数组。
    static void changeMaxAndMin(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要多大的数组：");
        int arrLength = input.nextInt();
        int [] arr  = new int[arrLength];
        for(int i = 0; i < arrLength; i++)
        {
            arr[i] = input.nextInt();//初始化数组
        }
        int max = arr[0] , min=arr[0] ,maxIndex = 0,minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {//找到数组的最大值索引
                max = arr[i];
                maxIndex = i;
            }
            else if(min > arr[i]) {//找到数组的最小值索引
                min = arr[i];
                minIndex = i;
            }
        }
        if(maxIndex != 0)//如果最大值的索引不是0，交换元素
        {
            arr[0] = arr[0] ^ arr[maxIndex];
            arr[maxIndex] = arr[0] ^ arr[maxIndex];
            arr[0] = arr[0] ^ arr[maxIndex];
        }
        if(minIndex != arrLength - 1 )//如果最大值的索引不是arrLength - 1，交换元素
        {
            arr[arrLength - 1] = arr[arrLength - 1] ^ arr[minIndex];
            arr[minIndex] = arr[arrLength - 1] ^ arr[minIndex];
            arr[arrLength - 1] = arr[arrLength - 1] ^ arr[minIndex];
        }
        for (int i = 0; i < arr.length; i++) {//输出数组
            System.out.println(arr[i]);
        }
    }

    //9.取一个整数 a 从右端开始的 4～7 位
    static void outputRightNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个大于7位数的整数：");
        long num = input.nextLong();//定义数值类型是long类型，防止越界
        String str = Long.toString(num);//将long类型转换成字符串
        char[] charStr = str.toCharArray();//利用字符串的方法转换为字符数组
        int length = charStr.length;
        if (length < 7) {//容错判断
            System.out.println("您输入的整数长度有误！");
        }
        else {//如果输入正确，输入该整数的倒数4-7位
            System.out.println("您输入的整数从右端开始的4-7位分别是："+
                    charStr[length-4] +" "+charStr[length-5]+" "
                    +charStr[length-6]+" "+charStr[length-7]);
        }
    }

    //10.将一个数组逆序输出
    static void reverseOrderOfArray(){
        Scanner input = new Scanner(System.in);
        int [] arr = new int [100];//初始化定义数组，默认长度为100；
        System.out.println("请输入多个正整数(输入-1结束)：");
        int i = 0;//定义i是为了知道数组中有多少个元素；
        do//用户do while循环是为了控制数组输入的结束；
        {
            arr[i] = input.nextInt();
            i++;
        }while(arr[i-1] != -1);//第一次到这里的时候，i已经是1，所以可以减去1
        System.out.println("您输入的数组是：");
        for (int j2 = 0; j2 < i-1; j2++) {//顺序输入刚才输入的数组
            System.out.println(arr[j2]+ " ");
        }
        System.out.println("您输入的数组逆序输出为：");
        for (int j2 = 0; j2 < i-1; j2++) {//逆序输入刚才输入的数组
            System.out.println(arr[i-2-j2] + " ");
        }
    }

    //11.将十个数进行排序
    static void sortNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入您要输入的个数");
        int num = input.nextInt();
        int [] arrInt = new int[num];
        System.out.println("输入"+num+"位数进行排序：");
        for (int i = 0; i < num; i++) {
            arrInt[i] = input.nextInt();
        }
        Arrays.sort(arrInt);//利用自带的排序函数进行排序
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println(arrInt[i]);
        }
    }

    //12.求 s=a+aa+aaa+aaaa+aa...a 的值，其中 a 是一个数字。例如 2+22+222+2222+22222(此时共有 5 个数相加)，几个数相加由键盘控制
    static void addNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入个位数字：");
        int single = input.nextInt();
        System.out.println("请输入最高位数：");
        int max = input.nextInt();
        int sum = 0,temp = 0;
        for (int i = 0; i < max; i++) {
            temp = single + temp;    //先把本次要加的值赋值给temp；
            single *= 10;            //每次把单数乘以10，向前进一位，加上之前的temp正好满足需要
            sum = sum + temp;        //把每次的temp相加起来就是要的结果
        }
        System.out.println("数字"+single+"公共有"+max+"个数相加的好结果为："+sum);
    }


    //13.求 1+2!+3!+...+20!的和
    static void sumOffactorial(){
        long sum = 0 ;long temp = 1;//必须要设置为long类型，不然超过范围；
        for (int i = 1; i <= 20; i++) {
            temp = 1;
            for (int j = 1; j <= i; j++) {
                temp *= j;
            }
            sum += temp;
        }
        System.out.println(sum);
    }

    //14 编写程序，输入一个字符，判断它是否为小写字母，如果是，将它转换成大
    static void caseSensitive(){

        //小写字母的ascll值为97-122
        //大写字母的ascll值为65-90
        System.out.println("请输入一个字母：\n");
        Scanner input = new Scanner(System.in);
        char charactor=input.next().charAt(0);
        if (charactor>=97&&charactor<=122){           //判断是否是小写字母
            System.err.println("该字母是小写字母");
            charactor=(char) (charactor-32);        //如果是小写字母则 将其转换成大写字母
            System.err.println("转换之后的大写字母是："+charactor);
        }
        else{
            System.out.println("该字母不是小写字母！");
        }
    }

    //15 输入三个数，看能够构成三角形
    static void constructTriangle(){
        int a;
        int b;
        int c;
        System.out.println("请输入三个正整数：");
        Scanner in=new Scanner(System.in);
        a=in.nextInt();
        b=in.nextInt();
        c=in.nextInt();
        if(a<=0||b<=0||c<=0) {
            System.out.println("输入的必须是正整数！");
        }
        if((a+b)>c&&(a+c)>b&&(b+c)>a) {
            System.out.println("能构成三角形！");
        }
        else{
            System.out.println("不能构成三角形！");
        }
    }

    //16 水仙花数是指一个 n 位数 ( n≥3 )，它的每个位上的数字的 n 次幂之和等
    //于它本身。（例如：1^3 + 5^3 + 3^3 = 153）。编程求出所有三位的水仙花数。
    static void findNumber(){
        for(int num=100;num<1000;num++){
            if(isTheNumber(num)){
                System.out.println(num);
            }
        }
    }

    //17 判断一个数是不是水仙花数
    public static boolean isTheNumber(int num){
        int b=num/100;
        int s=num%100/10;
        int g=num%10;
        return Math.pow(b, 3)
                +Math.pow(s, 3)
                +Math.pow(g, 3)==num?true:false;
    }

    //18 将一个正整数分解质因数。例如：输入 90，打印出 90=2*3*3*5。
    static void decomposeNumber(){
        System.out.println("请输入一个整数：");
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        System.out.println(num+"的质因数有：");
        for(int i=2;i<num;i++){
            while(num%i==0){
                num/=i;
                System.out.print(i+" ");
            }
        }
        System.out.print("  "+num);
    }

    //19 求解二元一次方程的解
    static void solveEquation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("输入2次方的系数");
        int a = sc.nextInt();
        System.out.println("输入1次方的系数");
        int b = sc.nextInt();
        System.out.println("输入0次方的系数");
        int c = sc.nextInt();
        if((b*b - 4*a*c)<0){     //  判断方程是否有解
            System.out.println("方程无解！");
            return;
        }
        else{
            System.out.println("方程有解！");
        }
        double x1 = (-b + Math.sqrt(b*b - 4*a*c))/2*a;
        double x2 = (-b - Math.sqrt(b*b - 4*a*c))/2*a;
        System.out.println("根分别是 " + x1 + "\t" + x2);
    }

    //20 输出九九乘法表
    static void multiplicationTable(){
        for (int i = 1; i < 10 ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }
    }
}

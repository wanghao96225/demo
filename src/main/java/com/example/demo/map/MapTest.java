package com.example.demo.map;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * @version 1.0.0
 * @ClassName MapTest.java
 * @createTime 2023年02月10日
 */
// 测试类型：吞吐量
@BenchmarkMode(Mode.Throughput)

@OutputTimeUnit(TimeUnit.MILLISECONDS)
// 预热2轮，每次1秒
@Warmup(iterations = 2,time=1,timeUnit = TimeUnit.SECONDS)
// 测试5轮，每次3秒
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
// 一个线程
@Fork(1)
// 每个测试线程一个实例
@State(Scope.Thread)
public class MapTest {

    static Map<Integer, String> map = new HashMap(){{
        for (int i = 0; i < 100; i++) {
            put(i, "val:"+i);
        }
    }};

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(MapTest.class.getSimpleName())
                // 输出测试结果的路径及文件名
                .output("/Users/wanghao/Downloads/jmh-map.log")
                .build();
        new Runner(opt).run();

    }

    @Benchmark
    public void entrySet(){
        Iterator<Map.Entry<Integer,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Benchmark
    public void keySet(){
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    @Benchmark
    public void forEachEntrySet(){
        for (Map.Entry< Integer,String> entry: map.entrySet()){
            System.out.println(entry.getKey() + " "+entry.getValue());
        }
    }

    @Benchmark
    public void forEachKeySet() {
        for (Integer key: map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    @Benchmark
    public void lambda(){
        map.forEach((k, v) -> {
            System.out.println(k+" "+v);
        });
    }

    @Benchmark
    public void streamApi(){
        map.entrySet().stream().forEach((entry) ->{
            System.out.println(entry.getKey() + " "+entry.getValue());
        });
    }

    @Benchmark
    public void parallelStreamApi(){
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey() + " "+entry.getValue());
        });
    }
}

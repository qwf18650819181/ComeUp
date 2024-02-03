package javamicrobenchmarkharness;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

/**
 * @auth: qwf
 * @date: 2023年12月26日 0026
 * @description:
 */
public class Test {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }


}

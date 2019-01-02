package org.hut.flink.batch.map;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Created by hutwanghui on 2018/12/25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
public class FlinkDemoPageRank implements FlatJoinFunction<Tuple2<Long, Double>, Tuple2<Long, Long[]>, Tuple2<Long, Double>> {
    @Override
    public void join(Tuple2<Long, Double> page, Tuple2<Long, Long[]> adj, Collector<Tuple2<Long, Double>> collector) throws Exception {
        Long[] neighbors = adj.f1;
        double rank = page.f1;
        double rankToDistribute = rank/((double)neighbors.length);

        for (int i = 0; i < neighbors.length; i++) {
            collector.collect(new Tuple2<Long, Double>(neighbors[i], rankToDistribute));
        }
    }
}

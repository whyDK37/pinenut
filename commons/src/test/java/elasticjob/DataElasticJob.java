package elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.Collections;
import java.util.List;

public class DataElasticJob implements DataflowJob<String> {
    
    @Override
    public List<String> fetchData(ShardingContext context) {
        System.out.println("fetch data.."+context.toString());
        context.getJobName();
        context.getJobParameter();
        context.getShardingItem();
        context.getShardingParameter();
        context.getShardingTotalCount();
        switch (context.getShardingItem()) {
            case 0: 
//                List<Foo> data = // get data from database by sharding item 0
                return DataDemo.getL1();
            case 1: 
//                List<Foo> data = // get data from database by sharding item 1
                return DataDemo.getL2();
            case 2: 
//                List<Foo> data = // get data from database by sharding item 2
                return DataDemo.getL3();
            // case n: ...
        }

        return null;
    }
    
    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        System.out.println("process data......"+ data.toString());
        // process data
        // ...
    }
}
package elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;

public class DataElasticJob implements DataflowJob<Foo> {
    
    @Override
    public List<Foo> fetchData(ShardingContext context) {
        System.out.println("fetch data..");
        switch (context.getShardingItem()) {
            case 0: 
//                List<Foo> data = // get data from database by sharding item 0
                return null;
            case 1: 
//                List<Foo> data = // get data from database by sharding item 1
                return null;
            case 2: 
//                List<Foo> data = // get data from database by sharding item 2
                return null;
            // case n: ...
        }

        return null;
    }
    
    @Override
    public void processData(ShardingContext shardingContext, List<Foo> data) {
        System.out.println("process data......");
        // process data
        // ...
    }
}
package aliyun.ots;

import com.aliyun.openservices.ots.model.*;
import com.aliyun.openservices.ots.model.condition.ColumnCondition;
import com.aliyun.openservices.ots.utils.Preconditions;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by whydk on 2016/7/11.
 */
public class OTSQuery<T> {
    public enum CompareOperator {
        EQUAL, GREATER_EQUAL, LESS_EQUAL
    }

//    private OTSTable otsTable = new OTSTable();

    private Class<T> requiredType;

    private String tablename;

    private Map<String, PrimaryKeyEntry> pk = new LinkedHashMap<String, PrimaryKeyEntry>();

    private List<String> columnsToGet;

    private int limit;
    private Direction direction = Direction.FORWARD;
    private ColumnCondition filter;

    public PrimaryKeyEntry addPrimaryKey(String key, Object value, PrimaryKeyType primaryKeyType, CompareOperator compareOperator) {
        Preconditions.checkArgument(pk.size() < 4, "The number of primary key columns must be in range: [1, 4]");
        return pk.put(key, new PrimaryKeyEntry(key, value, primaryKeyType, compareOperator));
    }

    public PrimaryKeyEntry removePrimaryKey(String key) {
        return pk.remove(key);
    }

    public PrimaryKeyEntry getPrimaryKey(String key) {
        return pk.get(key);
    }

    public RowPrimaryKey getStartPrimaryKey() {
        RowPrimaryKey start = new RowPrimaryKey();
        PrimaryKeyValue defaultVaue = getDefaultStartPrimaryKeyValue();

        for (Map.Entry<String, PrimaryKeyEntry> pkEntry : pk.entrySet()) {
            start.addPrimaryKeyColumn(pkEntry.getKey(), buildStartPrimaryKeyValue(pkEntry.getValue(), defaultVaue));
        }


//        if (direction == Direction.FORWARD) {
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MIN : PrimaryKeyValue.fromLong(topicid));
//            pi = 0;
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MAX);
//        } else {
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(topicid));
//            pi = 0;
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MIN);
//        }
        return start;
    }

    public RowPrimaryKey getEndPrimaryKey() {
        RowPrimaryKey end = new RowPrimaryKey();
        PrimaryKeyValue defaultVaue = getDefaultEndPrimaryKeyValue();

        for (Map.Entry<String, PrimaryKeyEntry> pkEntry : pk.entrySet()) {
            end.addPrimaryKeyColumn(pkEntry.getKey(), buildEndStartPrimaryKeyValue(pkEntry.getValue(), defaultVaue));
        }


//        if (direction == Direction.FORWARD) {
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MIN : PrimaryKeyValue.fromLong(topicid));
//            pi = 0;
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MAX);
//        } else {
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(topicid));
//            pi = 0;
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MIN);
//        }
        return end;
    }

    private PrimaryKeyValue getDefaultStartPrimaryKeyValue() {
        PrimaryKeyValue defaultVaue;
        if (direction == Direction.FORWARD) {
            defaultVaue = PrimaryKeyValue.INF_MIN;
        } else {
            defaultVaue = PrimaryKeyValue.INF_MAX;
        }
        return defaultVaue;
    }

    private PrimaryKeyValue getDefaultEndPrimaryKeyValue() {
        PrimaryKeyValue defaultVaue;
        if (direction == Direction.FORWARD) {
            defaultVaue = PrimaryKeyValue.INF_MAX;
        } else {
            defaultVaue = PrimaryKeyValue.INF_MIN;
        }
        return defaultVaue;
    }

    private PrimaryKeyValue buildStartPrimaryKeyValue(PrimaryKeyEntry value, PrimaryKeyValue defaultVaue) {
        PrimaryKeyValue pkv = defaultVaue;
        if (value.getValue() != null) {
            if (value.getPrimaryKeyType() == PrimaryKeyType.STRING&& Long.valueOf((Long) value.getValue()) > -1) {
                pkv = PrimaryKeyValue.fromString((String) value.getValue());
            } else if (value.getPrimaryKeyType() == PrimaryKeyType.INTEGER ) {
                pkv = PrimaryKeyValue.fromLong((Long) value.getValue());
            } else if (value.getPrimaryKeyType() == PrimaryKeyType.BINARY) {
                pkv = PrimaryKeyValue.fromBinary((byte[]) value.getValue());
            }
        }
        return pkv;
    }


    private PrimaryKeyValue buildEndStartPrimaryKeyValue(PrimaryKeyEntry value, PrimaryKeyValue defaultVaue) {
        PrimaryKeyValue pkv = defaultVaue;
        if (value.getValue() != null) {
            if (value.getCompareOperator() == CompareOperator.EQUAL) {
                return buildStartPrimaryKeyValue(value, defaultVaue);
            } else if (value.getCompareOperator() == CompareOperator.GREATER_EQUAL || value.getCompareOperator() == CompareOperator.LESS_EQUAL) {
                pkv = defaultVaue;
            }
        }
        return pkv;
    }


    public RangeRowQueryCriteria getRangeRowQueryCriteria() {
        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(this.getTablename());
        criteria.setInclusiveStartPrimaryKey(this.getStartPrimaryKey());
        criteria.setExclusiveEndPrimaryKey(this.getEndPrimaryKey());
        // 需要设置正确的limit，这里期望读出的数据行数最多就是完整的一页数据以及需要过滤(offset)的数据
        criteria.setLimit(limit);
        if (direction != null) criteria.setDirection(direction);
        if (columnsToGet != null) criteria.setColumnsToGet(columnsToGet);
        if (filter != null) criteria.setFilter(filter);

        return criteria;
    }


    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setColumnsToGet(List<String> columnsToGet) {
        this.columnsToGet = columnsToGet;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setFilter(ColumnCondition filter) {
        this.filter = filter;
    }

    public Class<T> getRequiredType() {
        return requiredType;
    }

    public String getTablename() {
        return tablename;
    }

    public Map<String, PrimaryKeyEntry> getPrimaryKey() {
        return Collections.unmodifiableMap(pk);
    }

    public List<String> getColumnsToGet() {
        return columnsToGet;
    }

    public int getLimit() {
        return limit;
    }

    public Direction getDirection() {
        return direction;
    }

    public ColumnCondition getFilter() {
        return filter;
    }

    public void setRequiredType(Class<T> requiredType) {
        this.requiredType = requiredType;
    }

    public final class PrimaryKeyEntry {
        String name;
        Object value;
        PrimaryKeyType primaryKeyType;
        CompareOperator compareOperator;

        public PrimaryKeyEntry(String name, Object value, PrimaryKeyType primaryKeyType, CompareOperator compareOperator) {
            this.name = name;
            this.value = value;
            this.primaryKeyType = primaryKeyType;
            this.compareOperator = compareOperator;
        }

        public CompareOperator getCompareOperator() {
            return compareOperator;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

        public PrimaryKeyType getPrimaryKeyType() {
            return primaryKeyType;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public void setPrimaryKeyType(PrimaryKeyType primaryKeyType) {
            this.primaryKeyType = primaryKeyType;
        }
    }
}

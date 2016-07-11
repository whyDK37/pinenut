package aliyun.ots;

import com.aliyun.openservices.ots.model.CapacityUnit;
import com.aliyun.openservices.ots.model.PrimaryKeyType;
import com.aliyun.openservices.ots.utils.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by whydk on 2016/7/1.
 */
public class OTSTable {

    /**
     * 表的名称。
     */
    private String tableName;

    /**
     * 表的主键字典。
     * 字典内的主键是有顺序的，顺序与用户添加主键的顺序相同。
     */
    private Map<String, PrimaryKeyType> primaryKey = new LinkedHashMap<String, PrimaryKeyType>();
    /**
     * 数据列
     */
    private Map<String, PrimaryKeyType> columns = new LinkedHashMap<String, PrimaryKeyType>();

    public static final int MAX_PRIMARY_KEY = 4;

    /**
     * 表的读CapacityUnit设置。
     */
    private int readCapacityUnit = 0;

    /**
     * 表的写CapacityUnit设置。
     */
    private int writeCapacityUnit = 0;

    protected OTSTable() {
    }

    public OTSTable(String tableName) {
        this();
        this.tableName = tableName;
    }

    public OTSTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public OTSTable setPrimaryKey(Map<String, PrimaryKeyType> primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public OTSTable addPrimaryKey(String key, PrimaryKeyType keyType) {
        Preconditions.checkArgument(primaryKey.size() < MAX_PRIMARY_KEY, "The number of primary key columns must be in range: [1, 4]");
        primaryKey.put(key, keyType);
        return this;
    }

    public OTSTable addPrimaryKey(String[] keys, PrimaryKeyType[] keyTypes) {
        Preconditions.checkArgument(primaryKey.size() < MAX_PRIMARY_KEY, "The number of primary key columns must be in range: [1, 4]");
        for (int i = 0; i < keys.length; i++) {
            addPrimaryKey(keys[i], keyTypes[i]);
        }
        return this;
    }

    public OTSTable setReadCapacityUnit(int readCapacityUnit) {
        this.readCapacityUnit = readCapacityUnit;
        return this;
    }

    public OTSTable setWriteCapacityUnit(int writeCapacityUnit) {
        this.writeCapacityUnit = writeCapacityUnit;
        return this;
    }

    public OTSTable setCapacity(int readCapacity, int writeCapacity) {
        setReadCapacityUnit(readCapacity);
        setWriteCapacityUnit(writeCapacity);
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, PrimaryKeyType> getPrimaryKey() {
        Preconditions.checkArgument(primaryKey.size() != 0, "The number of primary key columns must be in range: [1, 4]");
        return primaryKey;
    }

    public int getReadCapacityUnit() {
        return readCapacityUnit;
    }

    public int getWriteCapacityUnit() {
        return writeCapacityUnit;
    }

    public CapacityUnit getCapacityUnit() {
        return new CapacityUnit(this.readCapacityUnit, this.writeCapacityUnit);
    }
}

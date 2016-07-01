package aliyun.ots;

import com.aliyun.openservices.ots.model.CapacityUnit;
import com.aliyun.openservices.ots.model.PrimaryKeyType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by whydk on 2016/7/1.
 */
public class Table {

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
     * 表的读CapacityUnit设置。
     */
    private int readCapacityUnit = 0;

    /**
     * 表的写CapacityUnit设置。
     */
    private int writeCapacityUnit = 0;

    public Table() {
    }

    public Table(String tableName) {
        this();
        this.tableName = tableName;
    }

    public Table setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Table setPrimaryKey(Map<String, PrimaryKeyType> primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public Table addPrimaryKey(String key, PrimaryKeyType keyType) {
        primaryKey.put(key, keyType);
        return this;
    }

    public Table setReadCapacityUnit(int readCapacityUnit) {
        this.readCapacityUnit = readCapacityUnit;
        return this;
    }

    public Table setWriteCapacityUnit(int writeCapacityUnit) {
        this.writeCapacityUnit = writeCapacityUnit;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, PrimaryKeyType> getPrimaryKey() {
        // todo 处理
        if (primaryKey.size() == 0) ;
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

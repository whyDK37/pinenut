package com.aliyun.ots;

import com.aliyun.openservices.ots.model.CapacityUnit;
import com.aliyun.openservices.ots.model.ColumnType;
import com.aliyun.openservices.ots.model.PrimaryKeyType;
import com.aliyun.openservices.ots.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;

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
    private List<OTSPrimaryKey> primaryKeys = new ArrayList<OTSPrimaryKey>();
    /**
     * 数据列
     */
    private List<OTSColumn> columns = new ArrayList<OTSColumn>();

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

    public OTSTable addPrimaryKey(String key, PrimaryKeyType keyType) {
        Preconditions.checkArgument(primaryKeys.size() < MAX_PRIMARY_KEY, "The number of primary key columns must be in range: [1, 4]");
        OTSPrimaryKey primaryKey = new OTSPrimaryKey(key, keyType);
        primaryKeys.add(primaryKey);
        return this;
    }


    public OTSTable addColumn(String key, ColumnType keyType) {

        OTSColumn column = new OTSColumn(key, keyType);
        columns.add(column);
        return this;
    }

    public OTSTable addPrimaryKey(String[] keys, PrimaryKeyType[] keyTypes) {
        Preconditions.checkArgument(primaryKeys.size() < MAX_PRIMARY_KEY, "The number of primary key columns must be in range: [1, 4]");
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

    public List<OTSPrimaryKey> getPrimaryKey() {
        Preconditions.checkArgument(primaryKeys.size() != 0, "The number of primary key columns must be in range: [1, 4]");
        return primaryKeys;
    }

    public OTSPrimaryKey getPrimaryKey(int index) {
        Preconditions.checkArgument(index > primaryKeys.size(), "primary key out of range, please check out index");
        return primaryKeys.get(index-1);
    }

    public List<OTSColumn> getColumns() {
        return columns;
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


    public static class OTSPrimaryKey {
        private String key;
        private PrimaryKeyType keyType;

        public OTSPrimaryKey(String key, PrimaryKeyType keyType) {
            this.key = key;
            this.keyType = keyType;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public PrimaryKeyType getKeyType() {
            return keyType;
        }

        public void setKeyType(PrimaryKeyType keyType) {
            this.keyType = keyType;
        }
    }

    public static class OTSColumn {
        private String key;
        private ColumnType keyType;

        public OTSColumn(String key, ColumnType keyType) {
            this.key = key;
            this.keyType = keyType;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public ColumnType getKeyType() {
            return keyType;
        }

        public void setKeyType(ColumnType keyType) {
            this.keyType = keyType;
        }
    }
}

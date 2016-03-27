package pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StringBuilderFactory
        extends BasePooledObjectFactory<StringBuilder> {

    @Override
    public StringBuilder create() {
        return new StringBuilder();
    }

    /**
     * Use the default PooledObject implementation.
     */
    @Override
    public PooledObject<StringBuilder> wrap(StringBuilder buffer) {
        return new DefaultPooledObject<StringBuilder>(buffer);
    }

    /**
     * When an object is returned to the pool, clear the buffer.
     */
    @Override
    public void passivateObject(PooledObject<StringBuilder> pooledObject) {
        pooledObject.getObject().setLength(0);
    }

    // for all other methods, the no-op implementation
    // in BasePooledObjectFactory will suffice
}
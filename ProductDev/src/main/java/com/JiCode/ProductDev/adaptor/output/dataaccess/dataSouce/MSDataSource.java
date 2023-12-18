package com.JiCode.ProductDev.adaptor.output.dataaccess.dataSouce;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MSDataSource extends AbstractRoutingDataSource {

    // 从库的 Key 列表
    private List<Object> slaveKeys;

    // 从库 key 列表的索引，用原子 int 是为了保证多线程数据安全
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {

        // 当前业务的读写标识，之后写一个判断吧，可以根据当前请求的url，先写死
        // 当前线程的主从标识
        Boolean master = MSDataSourceMarker.get();

        if (master == null || master || this.slaveKeys.isEmpty()) {
            // 主库，返回 null，使用默认数据源
            return null;
        }

        // 从库，从 slaveKeys 中选择一个 Key
        int index = this.index.getAndIncrement() % this.slaveKeys.size();

        // 保证 int 数值的安全
        if (this.index.get() > 9999999) {
            this.index.set(0);
        }

        Object key = slaveKeys.get(index);

        return key;
    }

    public List<Object> getSlaveKeys() {
        return slaveKeys;
    }

    public void setSlaveKeys(List<Object> slaveKeys) {
        this.slaveKeys = slaveKeys;
    }
}

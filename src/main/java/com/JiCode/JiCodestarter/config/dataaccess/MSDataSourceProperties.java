package com.JiCode.JiCodestarter.config.dataaccess;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "app.datasource") // 配置前缀
public class MSDataSourceProperties {

    // 主库
    private final Properties master;

    // 从库
    private final Map<String, Properties> slave;

    @ConstructorBinding // 通过构造函数注入配置文件中的值
    public MSDataSourceProperties(Properties master, Map<String, Properties> slave) {
        super();

        Objects.requireNonNull(master);
        Objects.requireNonNull(slave);

        this.master = master;
        this.slave = new HashMap<>();
    }

    public Properties master() {
        return master;
    }

    public Map<String, Properties> slave() {
        return slave;
    }
}
package api.clients;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConf();

    static Config readConf() {
        return ConfigFactory.load("application.conf");
    }

    String API_KEY = config.getString("key");
}
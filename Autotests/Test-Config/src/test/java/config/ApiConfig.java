package config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        //"system:properties",
        "file:/tmp/apiConfig.properties",
        "classpath:apiConfig.properties"
})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("http://url/default")
    String getBaseUrl();

    @Key("token")
    @DefaultValue("default_token")
    String getToken();
}

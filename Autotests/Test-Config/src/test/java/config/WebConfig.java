package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:web${location}Config.properties"
})
public interface WebConfig extends Config {

    @Key("browserName")
    @DefaultValue("Firefox")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("100")
    String getBrowserVersion();

    @Key("remoteWebDriver")
    @DefaultValue("false")
    boolean getRemoteWebDriver();
}


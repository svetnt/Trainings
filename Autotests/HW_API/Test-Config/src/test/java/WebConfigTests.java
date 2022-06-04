import config.ApiConfig;
import config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebConfigTests {
    @Test
    public void testLocal() {
        //System.setProperty("location", "Local");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        assertThat(config.getRemoteWebDriver()).isEqualTo(false);
        assertThat(config.getBrowserName()).isEqualTo("Chrome");
        assertThat(config.getBrowserVersion()).isEqualTo("99");
    }

    @Test
    public void testRemote() {
        //System.setProperty("location", "Remote");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        assertThat(config.getRemoteWebDriver()).isEqualTo(true);
        assertThat(config.getBrowserName()).isEqualTo("Opera");
        assertThat(config.getBrowserVersion()).isEqualTo("98");
    }
}

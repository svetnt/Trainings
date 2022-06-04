import config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiConfigTests {
    @Test
    public void testPropertiesFile() {
        ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

        assertThat(config.getBaseUrl()).isEqualTo("http://url/in_file");
        assertThat(config.getToken()).isEqualTo("token_in_file");
    }

}

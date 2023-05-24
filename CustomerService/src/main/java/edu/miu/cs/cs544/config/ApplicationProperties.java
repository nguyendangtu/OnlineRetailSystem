package edu.miu.cs.cs544.config;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author : JOHNNGUYEN
 * @since : 5/16/2023, Tue
 **/
@ConfigurationProperties(prefix = "app")
@Validated
public class ApplicationProperties {
    @NotBlank
    private String name;
    @NotBlank
    private String version;
    private Server server = new Server();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Valid
    public class Server {
        @NotBlank
        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

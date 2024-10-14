package org.multitower.game.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gameserver")
@Data
public class GameServerConfig {
    String sessionCookieName = "USER";
}

/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppInfo {

    private final Environment env;
    private final BuildProperties build;

    public AppInfo(Environment env, BuildProperties build) {
        this.env = env;
        this.build = build;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void logApplicationStartup() {
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String serverPort = env.getProperty("server.port");

        String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path"))
            //.filter(StringUtils::isNotBlank)
            .orElse("/");
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("----------------------------------------------------------");
        log.info("Application {} is running!", env.getProperty("spring.application.name"));
        log.info("Access URLs:");
        log.info("Local: {}://localhost:{}{} ", protocol, serverPort, contextPath);
        log.info("External: {}://{}:{}{} ", protocol, hostAddress, serverPort, contextPath);
        log.info("Profile(s): {}", Arrays.toString(env.getActiveProfiles()));
        log.info("Git branch: {} ", env.getProperty("maven.branch-name"));
        log.info("Build info: {} build {} time {}", build.getVersion(), env.getProperty("maven.build-number"), build.getTime());
        log.info("PID {}", ProcessHandle.current().pid());
        log.info("----------------------------------------------------------");

    }

}


package com.akulinski.r8meservice;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

import java.util.concurrent.atomic.AtomicBoolean;

public class RedisTestContainerExtension implements BeforeAllCallback {
    private static AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if(!started.get()) {
            GenericContainer redis =
                new GenericContainer("redis:5.0.6")
                    .withExposedPorts(6379);
            redis.start();

            GenericContainer elastic = new GenericContainer("docker.elastic.co/elasticsearch/elasticsearch:6.4.3").withExposedPorts(9200);
            elastic.start();

            System.setProperty("data.jest.uri"," http://"+elastic.getContainerIpAddress()+":"+elastic.getMappedPort(9200));
            System.setProperty("redis.test.server", "redis://" + redis.getContainerIpAddress() + ":" + redis.getMappedPort(6379));
            started.set(true);
        }
    }
}

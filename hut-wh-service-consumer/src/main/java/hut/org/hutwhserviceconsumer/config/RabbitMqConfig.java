package hut.org.hutwhserviceconsumer.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue logQueue() {
        return new Queue("log");
    }
}

package br.com.pedroyodasaito.softdesign.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public static final String FILA_NAME_VOTACAO = "softdesign_teste";
    public static final String FILA_NAME_CONTABILIZAR_VOTACAO = "softdesign_teste2";

    @Bean
    Queue queueVotacao() {
        return new Queue(FILA_NAME_VOTACAO, true);
    }

    @Bean
    Queue queueContabilizar() {
        return new Queue(FILA_NAME_CONTABILIZAR_VOTACAO, true);
    }
}

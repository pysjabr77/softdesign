package br.com.pedroyodasaito.softdesign.mensageria;

import br.com.pedroyodasaito.softdesign.entity.Pauta;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class FilaEnvio {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    public FilaEnvio(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void enviar(Voto voto) {
        System.out.println("Enviando msg: " + voto.toString());
        rabbitTemplate.convertAndSend(queue.getName(), voto);
    }
}

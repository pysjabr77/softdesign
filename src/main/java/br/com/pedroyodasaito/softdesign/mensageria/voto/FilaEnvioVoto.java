package br.com.pedroyodasaito.softdesign.mensageria.voto;

import br.com.pedroyodasaito.softdesign.entity.Voto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class FilaEnvioVoto {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    public FilaEnvioVoto(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void enviar(Voto voto) {
        System.out.println("Enviando msg: " + voto.toString());
        rabbitTemplate.convertAndSend(queue.getName(), voto);
    }
}

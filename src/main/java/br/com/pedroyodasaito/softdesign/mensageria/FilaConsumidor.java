package br.com.pedroyodasaito.softdesign.mensageria;

import br.com.pedroyodasaito.softdesign.SoftdesignApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FilaConsumidor {

    @RabbitListener(queues = {SoftdesignApplication.filaName})
    public void receberMsg(@Payload String msg) {
        System.out.println("Mensagem recebida: " + msg);
    }

}

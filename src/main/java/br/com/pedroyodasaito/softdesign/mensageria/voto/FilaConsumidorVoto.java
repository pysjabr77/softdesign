package br.com.pedroyodasaito.softdesign.mensageria.voto;

import br.com.pedroyodasaito.softdesign.SoftdesignApplication;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import br.com.pedroyodasaito.softdesign.service.VotoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FilaConsumidorVoto {

    private final VotoService service;

    public FilaConsumidorVoto(VotoService service) {
        this.service = service;
    }

    @RabbitListener(queues = {SoftdesignApplication.filaName})
    public void receberMsg(@Payload Voto voto) {
        System.out.println("Mensagem recebida: " + voto.toString());
        service.consolidarVoto(voto);
    }

}

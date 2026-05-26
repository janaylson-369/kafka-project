package com.example.pedido_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3001") // Libera o acesso para o seu React
public class PedidoController {

    @Autowired
    private KafkaTemplate<String, PedidoRecord> kafkaTemplate;

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody PedidoRecord pedido) {
        // Envia o pedido de forma assíncrona para o tópico "pedidos-novos"
        // Usamos o nome do produto como chave da mensagem
        kafkaTemplate.send("pedidos-novos", pedido.produto(), pedido);
        
        System.out.println("=> Evento enviado ao Kafka: " + pedido.produto());
        
        return ResponseEntity.ok("Pedido enviado para a fila do Kafka com sucesso!");
    }
}

package com.example.notificacao_service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {
    @KafkaListener(topics = "pedidos-novos", groupId = "grupo-seminario")
    public void ouvirPedidos(PedidoRecord pedido) {
        System.out.println("--------------------------------------------------");
        System.out.println("📥 [Kafka] Novo evento capturado pelo Consumidor!");
        System.out.println("📦 Produto: " + pedido.produto());
        System.out.println("💰 Valor: R$ " + pedido.valor());
        System.out.println("✅ Notificação gerada com sucesso no Java 26!");
        System.out.println("--------------------------------------------------");
    }
}

package br.com.teste.service
;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import br.com.teste.domain.Pedido;


public interface PedidoService {
	
	ResponseEntity<String> processarPedido(List<Pedido> pedidos);

}

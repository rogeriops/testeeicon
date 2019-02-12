package br.com.teste.util;

import java.util.List;

import br.com.teste.domain.Pedido;
import br.com.teste.dto.RespostaDTO;
import br.com.teste.repository.PedidoRepository;

public class PedidoValidationUtil {

	public static void checkSizePedido(final List<Pedido> pedidos) throws IllegalArgumentException {

		if (pedidos.size() > 10) {
			throw new IllegalArgumentException("Enviar no máximo 10 pedidos por solicitação");
		}
	}

	public static void checkDuplicateSizePedido(final List<Pedido> pedidos, PedidoRepository pedidoRepository) throws IllegalArgumentException {
		
		StringBuilder pedidosDuplicados = new StringBuilder();
		boolean controle=false;
			
		for(Pedido elem : pedidos) {
			if(pedidoRepository.existsById(elem.getNumControle())) {
				pedidosDuplicados = pedidosDuplicados.append(" " + elem.getNumControle());
				controle=true;
			}
		}
		

		if (controle) {
			throw new IllegalArgumentException( "Não é possível processar pedidos duplicados, número de pedido em duplicidade "  + pedidosDuplicados.toString() );
		}
	}

}

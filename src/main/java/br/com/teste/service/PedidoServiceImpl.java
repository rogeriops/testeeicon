package br.com.teste.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.domain.Pedido;
import br.com.teste.repository.PedidoRepository;
import br.com.teste.util.PedidoValidationUtil;


@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {
	
	public static Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class.getName());
	
	public PedidoRepository pedidoRepository;
	
	@Autowired
	public PedidoServiceImpl( PedidoRepository pedidoRepository) {
		this.pedidoRepository=pedidoRepository;
	}
	
	public  ResponseEntity<String> processarPedido(List<Pedido> pedidos) {
		
		 try {
				PedidoValidationUtil.checkSizePedido(pedidos);
				
				PedidoValidationUtil.checkDuplicateSizePedido(pedidos,pedidoRepository );
				
				pedidoRepository.saveAll(pedidos);
			
				return new ResponseEntity<String>("Processamento efetuado com sucesso", HttpStatus.CREATED);
		
		} catch (final IllegalArgumentException ex) {
	          LOGGER.severe("M=processarPedido, error=" + ex.getMessage());
	          return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
		
	}
	

}

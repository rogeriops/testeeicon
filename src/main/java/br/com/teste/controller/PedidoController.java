package br.com.teste.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.domain.Pedido;
import br.com.teste.dto.RespostaDTO;
import br.com.teste.repository.PedidoRepository;
import br.com.teste.service.PedidoService;

@RestController
//@Slf4j

public class PedidoController {

	
	private final PedidoService pedidoService;
	private final PedidoRepository pedidoRepository;
	
	@Autowired
	public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
		this.pedidoService=pedidoService;
		this.pedidoRepository =pedidoRepository;
	}
	
	@GetMapping("/pedidos")
	List<Pedido> all(){
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/pedido/{id}")
	Pedido one(@PathVariable Long id) {
		return pedidoRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Não foi encontrado pedido para o número informado"));
	}
	@GetMapping("/pedidos/data/{data}")
	List<Pedido> oneDate(@PathVariable(name="data") String data) throws ParseException {
		
		return pedidoRepository.findAllByDtCadastro( new SimpleDateFormat("yyyy-MM-dd").parse(data));
	}

	@PostMapping("/pedido/novo")
	public ResponseEntity<String> novo(@RequestBody List<Pedido> pedidos) {
		
		return pedidoService.processarPedido(pedidos);
	}
}

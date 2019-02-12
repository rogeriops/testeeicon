package br.com.teste.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.teste.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	//@Query("Select c from Pedido c where c.dtCadastro  = :dtCadastro")
	public List<Pedido> findAllByDtCadastro(Date date);


}

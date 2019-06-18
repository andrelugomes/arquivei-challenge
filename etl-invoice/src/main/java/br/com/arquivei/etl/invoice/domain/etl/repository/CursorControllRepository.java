package br.com.arquivei.etl.invoice.domain.etl.repository;

import br.com.arquivei.etl.invoice.domain.etl.CursorControll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursorControllRepository extends JpaRepository<CursorControll, Long> {

}

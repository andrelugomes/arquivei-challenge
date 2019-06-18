package br.com.arquivei.etl.invoice.domain.etl.service;

import br.com.arquivei.etl.invoice.domain.apiclient.Page;
import br.com.arquivei.etl.invoice.domain.etl.CursorControll;
import br.com.arquivei.etl.invoice.domain.etl.helper.CursorHelper;
import br.com.arquivei.etl.invoice.domain.etl.repository.CursorControllRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursorControllService {

  private final CursorControllRepository repository;
  private final Long id;
  private final Long cursor;

  public CursorControllService(final CursorControllRepository repository,
          @Value("${etl.cursor.control.id}") final Long id,
          @Value("${etl.cursor.control.position.start}") final long cursor) {
    this.repository = repository;
    this.id = id;
    this.cursor = cursor;
  }

  @Transactional(readOnly = true)
  public CursorControll current() {
    return repository.findById(id).orElse(new CursorControll(id,cursor));
  }

  public CursorControll update(final CursorControll controll, final Page page) {
    controll.setPosition(CursorHelper.extractFrom(page));
    return repository.save(controll);
  }
}

package br.com.arquivei.etl.invoice.domain.etl.service;

import br.com.arquivei.etl.invoice.domain.apiclient.Page;
import br.com.arquivei.etl.invoice.domain.etl.CursorControll;
import br.com.arquivei.etl.invoice.domain.etl.repository.CursorControllRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursorControllServiceTest {

  @Autowired
  private CursorControllService service;

  @Autowired
  private CursorControllRepository repository;

  @Before
  public void setUp() {
    repository.deleteAll();
  }

  @Test
  public void shouldGetCurrentCursorByDefault() {

    final CursorControll cursorControll = service.current();

    Assertions.assertThat(cursorControll.getId()).isEqualTo(1);
    Assertions.assertThat(cursorControll.getPosition()).isEqualTo(0);
  }

  @Test
  public void shouldUpdateCursor() {

    final CursorControll cursorControll = service.current();
    final Page page = new Page();
    page.setNext("https://apiuat.arquivei.com.br/v1/nfe/received?cursor=1000&limit=50");

    final CursorControll update = service.update(cursorControll, page);

    Assertions.assertThat(cursorControll.getId()).isEqualTo(1);
    Assertions.assertThat(cursorControll.getPosition()).isEqualTo(1000);
  }

}
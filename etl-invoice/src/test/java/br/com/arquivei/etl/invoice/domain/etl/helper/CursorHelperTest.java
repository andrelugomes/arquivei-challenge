package br.com.arquivei.etl.invoice.domain.etl.helper;

import br.com.arquivei.etl.invoice.domain.apiclient.Page;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CursorHelperTest {
  
  @Test(expected = RuntimeException.class)
  public void shouldPageNotBeNull() {
    CursorHelper.extractFrom(null);
  }

  @Test(expected = RuntimeException.class)
  public void shouldPageNextNotBeNull() {
    CursorHelper.extractFrom(new Page());
  }

  @Test
  public void shouldFindTheNextCursorFromResponse() {
    final Page page = new Page();
    page.setNext("https:\\/\\/apiuat.arquivei.com.br\\/v1\\/nfe\\/received??filter=(= processed false)&cursor=30394&limit=50");

    final Long next = CursorHelper.extractFrom(page);

    Assertions.assertThat(next).isEqualTo(30394);
  }

  @Test
  public void shouldFindTheNextCursor() {
    final Page page = new Page();
    page.setNext("https://apiuat.arquivei.com.br/v1/nfe/received?filter=(= processed false)&cursor=12345&limit=50");

    final Long next = CursorHelper.extractFrom(page);

    Assertions.assertThat(next).isEqualTo(12345);
  }

  @Test(expected = RuntimeException.class)
  public void shouldNotFindACursor() {
    final Page page = new Page();
    page.setNext("https://apiuat.arquivei.com.br/v1/nfe/received");

    CursorHelper.extractFrom(page);
  }

  @Test
  public void shouldHasntNextWhenCountIsZero() {
    final Page page = new Page();
    page.setNext("https://apiuat.arquivei.com.br/v1/nfe/received?cursor=12345&limit=50");

    final var hasNext = CursorHelper.hasNext(page,0);

    Assertions.assertThat(hasNext).isFalse();
  }

  @Test
  public void shouldHasntNextWhenCountIsZeroAndHasNoLink() {
    final Page page = new Page();
    page.setNext(null);

    final var hasNext = CursorHelper.hasNext(page,0);

    Assertions.assertThat(hasNext).isFalse();
  }

}
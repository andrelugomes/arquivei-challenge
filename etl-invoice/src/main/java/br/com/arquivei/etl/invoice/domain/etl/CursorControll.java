package br.com.arquivei.etl.invoice.domain.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cursor_controll")
public class CursorControll {

  @Id
  private Long id;

  @Column
  private Long position;

  public CursorControll() {
  }

  public CursorControll(final Long id, final Long position) {
    this.id = id;
    this.position = position;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Long getPosition() {
    return position;
  }

  public void setPosition(final Long position) {
    this.position = position;
  }
}

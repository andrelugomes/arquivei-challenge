package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlElement;

public class Total {

  public Total() {
  }

  private ICMSTot ICMSTot;

  public ICMSTot getICMSTot() {
    return ICMSTot;
  }

  @XmlElement(name = "ICMSTot")
  public void setICMSTot(final ICMSTot ICMSTot) {
    this.ICMSTot = ICMSTot;
  }
}

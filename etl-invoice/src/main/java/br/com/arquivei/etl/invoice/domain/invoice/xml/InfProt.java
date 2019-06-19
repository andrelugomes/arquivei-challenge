package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlElement;

public class InfProt {

  public InfProt() {
  }

  private String chNFe;

  @XmlElement(name = "chNFe")
  public String getChNFe() {
    return chNFe;
  }

  public void setChNFe(final String chNFe) {
    this.chNFe = chNFe;
  }
}

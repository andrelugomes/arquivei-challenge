package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlElement;

public class ProtNFe {

  public ProtNFe() {
  }

  private InfProt infProt;

  @XmlElement(name = "infProt")
  public InfProt getInfProt() {
    return infProt;
  }

  public void setInfProt(final InfProt infProt) {
    this.infProt = infProt;
  }
}

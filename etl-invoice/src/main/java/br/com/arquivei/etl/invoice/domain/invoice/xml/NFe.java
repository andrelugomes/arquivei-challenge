package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NFe", namespace = "http://www.portalfiscal.inf.br/nfe")
public class NFe {

  public NFe() {
  }

  private InfNFe infNFe;

  public InfNFe getInfNFe() {
    return infNFe;
  }

  @XmlElement(name = "infNFe")
  public void setInfNFe(final InfNFe infNFe) {
    this.infNFe = infNFe;
  }
}

package br.com.arquivei.etl.invoice.domain.invoice.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nfeProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class NfeProc {

  public NfeProc() {
  }

  private NFe nfe;

  @XmlElement(name = "NFe", required = true)
  public NFe getNFe() {
    return nfe;
  }

  public void setNFe(final NFe nfe) {
    this.nfe = nfe;
  }
}

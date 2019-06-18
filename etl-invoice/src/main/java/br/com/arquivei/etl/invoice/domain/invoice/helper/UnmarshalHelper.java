package br.com.arquivei.etl.invoice.domain.invoice.helper;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import br.com.arquivei.etl.invoice.domain.invoice.xml.NfeProc;

public class UnmarshalHelper {

  public static NfeProc toNfeProc(final String xmlAsString) throws JAXBException {

    final JAXBContext context = JAXBContext.newInstance(NfeProc.class);
    final var unmarshaller = context.createUnmarshaller();
    final var reader = new StringReader(xmlAsString);

    return (NfeProc) unmarshaller.unmarshal(reader);
  }
}

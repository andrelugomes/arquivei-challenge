package br.com.arquivei.etl.invoice.domain.etl.helper;

import java.util.regex.Pattern;

import br.com.arquivei.etl.invoice.domain.apiclient.Page;

public class CursorHelper {

  public static Long extractFrom(final Page page) {

    if (page == null || page.getNext() == null) {
      throw new RuntimeException("Page ou Next cannot be null");
    }

    final var nextPage = page.getNext();

    if (nextPage.contains("cursor")) {
      final var matcher = Pattern.compile("cursor=[0-9]+", Pattern.CASE_INSENSITIVE).matcher(nextPage);

      if (matcher.find()) {
        final var split = matcher.group(0).split("=");
        return Long.valueOf(split[1]);
      }
    }
    throw new RuntimeException("Next cursor not found");
  }

  public static boolean hasNext(final Page page, final Integer count) {
    return (page != null && page.getNext() != null) && count > 0;
  }
}

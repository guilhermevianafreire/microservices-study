package br.dev.gvf.productservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DatabaseLikeTypes {

  START_WITH("%s%%"),
  END_WITH("%%%s"),
  CONTAINS("%%%s%%");

  private final String pattern;

}

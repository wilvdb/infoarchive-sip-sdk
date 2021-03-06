/*
 * Copyright (c) 2016-2017 by OpenText Corporation. All Rights Reserved.
 */
package com.opentext.ia.sdk.sip;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.opentext.ia.sdk.support.io.ZipAssembler;

/**
 * The ContentAssembler implementation that ignores any digital objects. Useful for structured data only SIPs.
 * @param <D> The type of domain object to assemble SIPs from
 */
public class ContentAssemblerIgnore<D> implements ContentAssembler<D> {

  @Override
  public void begin(ZipAssembler zip, Counters metrics) {
    // NOP
  }

  @Override
  public Map<String, ContentInfo> addContentsOf(D domainObject) throws IOException {
    // NOP
    return Collections.emptyMap();
  }

}

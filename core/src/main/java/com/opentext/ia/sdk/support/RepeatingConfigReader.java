/*
 * Copyright (c) 2016-2017 by OpenText Corporation. All Rights Reserved.
 */
package com.opentext.ia.sdk.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Read sequences of repeating elements from a flat configuration.
 */
public class RepeatingConfigReader {

  private final String name;
  private final List<String> fields;

  public RepeatingConfigReader(String name, List<String> fields) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("RepeatingConfigReader.name cannot be null/empty.");
    }
    if (fields == null || fields.isEmpty()) {
      throw new IllegalArgumentException("RepeatingConfigReader.fields cannot be null/empty.");
    }
    this.name = name;
    this.fields = fields;
  }

  public List<Map<String, String>> read(Map<String, String> configuration) {
    List<List<String>> values = new ArrayList<>();
    List<Integer> sizes = new ArrayList<>(values.size());
    int nullCount = 0;
    for (String field : fields) {
      String rawFieldValue = configuration.get(field);
      if (rawFieldValue == null) {
        rawFieldValue = "";
        nullCount += 1;
      }
      List<String> fieldValues = getComponents(rawFieldValue);
      values.add(fieldValues);
      sizes.add(fieldValues.size());
    }

    if (nullCount == fields.size()) {
      return Collections.emptyList();
    }

    if (sizes.stream().distinct().count() > 1) {
      throw new IllegalArgumentException(formatErrorMessage(values, sizes));
    }
    return convertToListOfMaps(values);
  }

  private List<Map<String, String>> convertToListOfMaps(List<List<String>> values) {
    int numValues = values.get(0).size();
    List<Map<String, String>> result = new ArrayList<>();

    for (int valueIndex = 0; valueIndex < numValues; ++valueIndex) {
      Map<String, String> map = new HashMap<>();
      for (int i = 0; i < fields.size(); ++i) {
        List<String> fieldValue = values.get(i);
        map.put(fields.get(i), fieldValue.get(valueIndex));
      }
      result.add(map);
    }
    return result;
  }

  private String formatErrorMessage(List<List<String>> values, List<Integer> sizes) {
    StringBuilder result = new StringBuilder(256);
    result.append("All configuration items in the ")
        .append(name)
        .append(" configuration group must have the same number of values. Number of values found for each item: ");
    for (int i = 0; i < fields.size(); ++i) {
      if (i > 0) {
        result.append(", ");
      }
      result.append(fields.get(i))
          .append(": ")
          .append(sizes.get(i))
          .append(' ')
          .append(values.get(i));
    }
    result.append('.');
    return result.toString();
  }

  private static List<String> getComponents(String value) {
    List<String> result = new ArrayList<>();
    int offset = 0;
    for (int index = value.indexOf(',', offset); index != -1; index = value.indexOf(',', offset)) {
      result.add(value.substring(offset, index));
      offset = index + 1;
    }
    result.add(value.substring(offset, value.length()));
    return result;
  }

}

/*
 * Copyright (c) 2016-2017 by OpenText Corporation. All Rights Reserved.
 */
package com.opentext.ia.sdk.support;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Optional;


/**
 * Create instances of a configured class.
 */
public class NewInstance {

  public static NewInstance fromConfiguration(Map<String, String> configuration, String typeConfigurationName,
      String defaultTypeName) {
    return new NewInstance(configuration, typeConfigurationName, defaultTypeName);
  }

  private final String className;

  public NewInstance(Map<String, String> configuration, String classConfigurationName, String defaultClassName) {
    this(configuration.getOrDefault(classConfigurationName, defaultClassName));
  }

  private NewInstance(String className) {
    this.className = className;
  }

  public <T> T as(Class<T> type) {
    try {
      Class<?> implementationType = Class.forName(className);
      Constructor<?> constructor = implementationType.getDeclaredConstructor();
      constructor.setAccessible(true);
      return type.cast(constructor.newInstance());
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException("Failed to instantiate " + className, e);
    }
  }

  public static NewInstance of(String optionalClassName, String defaultClassName) {
    return new NewInstance(Optional.ofNullable(optionalClassName).orElse(defaultClassName));
  }

}

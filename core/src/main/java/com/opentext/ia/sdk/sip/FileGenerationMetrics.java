/*
 * Copyright (c) 2016-2017 by OpenText Corporation. All Rights Reserved.
 */
package com.opentext.ia.sdk.sip;

import java.io.File;

/**
 * Metrics about file generation.
 */
public class FileGenerationMetrics {

  private final File file;
  private final Metrics metrics;

  FileGenerationMetrics(File file, Metrics metrics) {
    this.file = file;
    this.metrics = metrics;
  }

  /**
   * Return the generated file.
   * @return The generated file
   */
  public File getFile() {
    return file;
  }

  /**
   * Return metrics about the file generation process.
   * @return Metrics about the file generation process
   */
  public Metrics getMetrics() {
    return metrics;
  }

  @Override
  public String toString() {
    return file.getPath() + ": " + metrics;
  }

}

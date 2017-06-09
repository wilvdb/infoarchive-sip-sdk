/*
 * Copyright (c) 2016-2017 by OpenText Corporation. All Rights Reserved.
 */
package com.opentext.ia.sdk.server.configuration;

import com.opentext.ia.sdk.client.api.ArchiveConnection;


/**
 * Interface hiding the details of how the application and holding configuration objects are created in the Archive.
 */
public interface InfoArchiveConfigurer {

  /**
   * Returns details about how to connect to the Archive.
   * @return details about how to connect to the Archive
   */
  ArchiveConnection getArchiveConnection();

  /**
   * Returns the name of the application to configure.
   * @return the name of the application to configure
   */
  String getApplicationName();

  /**
   * Configure the Archive for the application.
   */
  void configure();

}

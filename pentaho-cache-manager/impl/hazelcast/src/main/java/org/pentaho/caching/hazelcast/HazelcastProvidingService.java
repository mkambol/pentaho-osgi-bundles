/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2015 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.caching.hazelcast;

import com.hazelcast.cache.HazelcastCachingProvider;
import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import org.pentaho.caching.api.PentahoCacheSystemConfiguration;
import org.pentaho.caching.spi.AbstractCacheProvidingService;

import java.io.File;
import java.util.Properties;

public class HazelcastProvidingService extends AbstractCacheProvidingService {

  private static final HazelcastServerCachingProvider provider = new HazelcastServerCachingProvider();

  private static final String KARAF_ETC = System.getProperty("karaf.etc");
  private static final File DEFAULT_CONFIG = new File( KARAF_ETC + "/hazelcast.xml" );

  @Override public javax.cache.CacheManager createCacheManager( PentahoCacheSystemConfiguration systemConfiguration ) {
    Properties props = new Properties(  );
    props.put( HazelcastCachingProvider.HAZELCAST_CONFIG_LOCATION,
        DEFAULT_CONFIG.toURI().toString() );

    return provider
        .getCacheManager(
            DEFAULT_CONFIG.toURI(),
            getClass().getClassLoader(),  // use the CL from this bundle, so serialized classes are accessible.
                                          // this bundle is defined with DynamicImport-Package: *
            props );
  }
}

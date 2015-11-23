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

import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import org.junit.Test;
import org.pentaho.caching.api.PentahoCacheSystemConfiguration;

import javax.cache.CacheManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class HazelcastProvidingServiceTest {

  private HazelcastProvidingService service = new HazelcastProvidingService();

  @Test public void testCreateCacheManager() throws Exception {
    CacheManager cacheManager = service.createCacheManager( mock( PentahoCacheSystemConfiguration.class ) );

    assertNotNull( cacheManager );
    try {
      cacheManager.unwrap( HazelcastServerCacheManager.class );
    } catch ( IllegalArgumentException iae ) {
      fail( "Expected CacheManager to be backed by hazelcast CacheManager." );
    }
    cacheManager.close();
  }
}

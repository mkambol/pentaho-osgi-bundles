package org.pentaho.caching.hazelcast;

import com.hazelcast.core.Hazelcast;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
  @Override public void start( BundleContext bundleContext ) throws Exception {
    // Nothing to do here
  }

  @Override public void stop( BundleContext bundleContext ) throws Exception {
    // TODO:  this will shutdown all hazelcast instances running in the JVM,
    // not necessarily just those started by this provider.  Is that okay?
    Hazelcast.shutdownAll();
  }
}

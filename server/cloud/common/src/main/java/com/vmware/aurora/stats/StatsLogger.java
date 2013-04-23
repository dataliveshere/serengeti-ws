/***************************************************************************
 * Copyright (c) 2012-2013 VMware, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package com.vmware.aurora.stats;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.vmware.aurora.buildinfo.BuildConfig;

/**
 * A thread that dumps stats & runtime info periodically.
 */
public class StatsLogger extends Thread {
   // Use another logger to do non-stats log.
   protected static Logger logger = Logger.getLogger(StatsLogger.class);

   // The statsLogger is configured to write log into aurora-cms-stats.log.
   static protected Logger statsLogger;
   static final long REPORT_INTERVAL = TimeUnit.MINUTES.toNanos(2); // every 2 min
   static final int THREADS_LIMIT = 2000;          // limit of total number of threads
   static private StatsLogger loggerThread;
   static private boolean shutdown = false;

   private final Thread[] tarray;       // buffer to enumerate all threads

   static {
      statsLogger = Logger.getLogger("aurora-cms-stats");
      // XXX Can we put the following line in log4j.properties?
      statsLogger.setAdditivity(false);
   }

   static public void init() {
      loggerThread = new StatsLogger();
      loggerThread.start();
   }

   static public void shutdown() {
      shutdown = true;
   }

   final static void logStats(Object obj) {
      statsLogger.info(obj);
   }

   private StatsLogger() {
      setName("StatsLogger");
      setDaemon(true);
      // enough to report on threads double the limit
      tarray = new Thread[THREADS_LIMIT * 2];
   }

   private void checkThreadLimit() {
      if (Thread.activeCount() > THREADS_LIMIT && BuildConfig.debug) {
         int nThreads = Thread.enumerate(tarray);
         logger.warn("THREAD DUMP START");
         for (int i=0; i < nThreads; i++) {
            Thread t = tarray[i];
            logger.warn(t);
            StackTraceElement[] st = t.getStackTrace();
            for (int j=0; j < st.length; j++) {
               logger.warn("\tat" + st[j]);
            }
         }
         logger.warn("THREAD DUMP END");
      }
   }

   private void reportRuntimeInfo() {
      // assume a single thread group
      logStats(String.format("RUNTIME THREADS active %d.",
               Thread.activeCount()));
      logStats(String.format("RUNTIME MEMORY free %d total %d max %d.",
               Runtime.getRuntime().freeMemory(),
               Runtime.getRuntime().totalMemory(),
               Runtime.getRuntime().maxMemory()));
      checkThreadLimit();
   }

   private void reportInterval(long interval) {
      logStats("INTERVAL " + interval + " START");
      Profiler.logInterval(interval);
      reportRuntimeInfo();
      logStats("INTERVAL " + interval + " END");
   }

   @Override
   public void run() {
      long interval = 0;
      long lastReportTime = System.nanoTime();
      while (!shutdown) {
         long nextReportTime = lastReportTime + REPORT_INTERVAL;
         try {
            reportInterval(++interval);
            long curTime = System.nanoTime();
            if (nextReportTime > curTime) {
               sleep(TimeUnit.NANOSECONDS.toMillis(nextReportTime - curTime));
            }
            lastReportTime = nextReportTime;
         } catch (Throwable e) {
            // Take note of any exceptions and continue.
            logger.info("caught exception ", e);
         }
      }
   }
}
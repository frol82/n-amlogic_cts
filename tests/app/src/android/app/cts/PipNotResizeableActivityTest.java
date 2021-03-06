/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package android.app.cts;

import android.app.Instrumentation;
import android.app.stubs.PipNotResizeableActivity;
import android.test.ActivityInstrumentationTestCase2;

public class PipNotResizeableActivityTest
        extends ActivityInstrumentationTestCase2<PipNotResizeableActivity> {

        private Instrumentation mInstrumentation;
        private PipNotResizeableActivity mActivity;

        public PipNotResizeableActivityTest() {
            super("android.app.stubs", PipNotResizeableActivity.class);
        }

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            mInstrumentation = getInstrumentation();
            mActivity = getActivity();
        }

        public void testLaunchPipNotResizeableActivity() throws Throwable {
            runTestOnUiThread(new Runnable() {
                public void run() {
                    boolean pipSupportDisabled = false;
                    try {
                        mActivity.enterPictureInPictureMode();
                    } catch (IllegalStateException e) {
                        // Pip not supported
                        pipSupportDisabled = true;
                    } catch (IllegalArgumentException e) {
                        // Pip not supported
                        pipSupportDisabled = true;
                    }
                    assertTrue(pipSupportDisabled);
                    assertFalse(mActivity.isInPictureInPictureMode());
                }
            });
            mInstrumentation.waitForIdleSync();
        }
    }

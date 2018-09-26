package org.reactnative.facedetector;

import org.reactnative.facedetector.tasks.StreamingFileFaceDetectionAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class StreamingFaceDetectorModule extends ReactContextBaseJavaModule {
  private static final String TAG = "StreamingFaceDetector";
//  private ScopedContext mScopedContext;
private static ReactApplicationContext mScopedContext;

  public StreamingFaceDetectorModule(ReactApplicationContext reactContext) {
    super(reactContext);
    mScopedContext = reactContext;
  }

  @Override
  public String getName() {
    return TAG;
  }

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    return Collections.unmodifiableMap(new HashMap<String, Object>() {
      {
        put("Mode", getFaceDetectionModeConstants());
        put("Landmarks", getFaceDetectionLandmarksConstants());
        put("Classifications", getFaceDetectionClassificationsConstants());
      }

      private Map<String, Object> getFaceDetectionModeConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
          {
            put("fast", StreamingFaceDetector.FAST_MODE);
            put("accurate", StreamingFaceDetector.ACCURATE_MODE);
          }
        });
      }

      private Map<String, Object> getFaceDetectionClassificationsConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
          {
            put("all", StreamingFaceDetector.ALL_CLASSIFICATIONS);
            put("none", StreamingFaceDetector.NO_CLASSIFICATIONS);
          }
        });
      }

      private Map<String, Object> getFaceDetectionLandmarksConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
          {
            put("all", StreamingFaceDetector.ALL_LANDMARKS);
            put("none", StreamingFaceDetector.NO_LANDMARKS);
          }
        });
      }
    });
  }

  @ReactMethod
  public void detectFaces(ReadableMap options, final Promise promise) {
    new StreamingFileFaceDetectionAsyncTask(mScopedContext, options, promise).execute();
  }
}

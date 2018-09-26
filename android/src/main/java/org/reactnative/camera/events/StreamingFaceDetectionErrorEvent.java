package org.reactnative.camera.events;

import android.support.v4.util.Pools;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import org.reactnative.camera.CameraViewManager;
import org.reactnative.facedetector.StreamingFaceDetector;

public class StreamingFaceDetectionErrorEvent extends Event<StreamingFaceDetectionErrorEvent> {
  private static final Pools.SynchronizedPool<StreamingFaceDetectionErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
  private StreamingFaceDetector mFaceDetector;

  private StreamingFaceDetectionErrorEvent() {
  }

  public static StreamingFaceDetectionErrorEvent obtain(int viewTag, StreamingFaceDetector faceDetector) {
    StreamingFaceDetectionErrorEvent event = EVENTS_POOL.acquire();
    if (event == null) {
      event = new StreamingFaceDetectionErrorEvent();
    }
    event.init(viewTag, faceDetector);
    return event;
  }

  private void init(int viewTag, StreamingFaceDetector faceDetector) {
    super.init(viewTag);
    mFaceDetector = faceDetector;
  }

  @Override
  public short getCoalescingKey() {
    return 0;
  }

  @Override
  public String getEventName() {
    return CameraViewManager.Events.EVENT_ON_FACE_DETECTION_ERROR.toString();
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap map = Arguments.createMap();
    map.putBoolean("isOperational", mFaceDetector != null && mFaceDetector.isOperational());
    return map;
  }
}

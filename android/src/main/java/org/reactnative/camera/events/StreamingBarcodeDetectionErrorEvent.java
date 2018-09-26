package org.reactnative.camera.events;

import android.support.v4.util.Pools;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import org.reactnative.barcodedetector.StreamingBarcodeDetector;
import org.reactnative.camera.CameraViewManager;

public class StreamingBarcodeDetectionErrorEvent extends Event<StreamingBarcodeDetectionErrorEvent> {

  private static final Pools.SynchronizedPool<StreamingBarcodeDetectionErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
  private StreamingBarcodeDetector mBarcodeDetector;

  private StreamingBarcodeDetectionErrorEvent() {
  }

  public static StreamingBarcodeDetectionErrorEvent obtain(int viewTag, StreamingBarcodeDetector barcodeDetector) {
    StreamingBarcodeDetectionErrorEvent event = EVENTS_POOL.acquire();
    if (event == null) {
      event = new StreamingBarcodeDetectionErrorEvent();
    }
    event.init(viewTag, barcodeDetector);
    return event;
  }

  private void init(int viewTag, StreamingBarcodeDetector faceDetector) {
    super.init(viewTag);
    mBarcodeDetector = faceDetector;
  }

  @Override
  public short getCoalescingKey() {
    return 0;
  }

  @Override
  public String getEventName() {
    return CameraViewManager.Events.EVENT_ON_BARCODE_DETECTION_ERROR.toString();
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap map = Arguments.createMap();
    map.putBoolean("isOperational", mBarcodeDetector != null && mBarcodeDetector.isOperational());
    return map;
  }
}

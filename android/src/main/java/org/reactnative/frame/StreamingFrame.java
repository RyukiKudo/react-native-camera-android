package org.reactnative.frame;

import org.reactnative.camera.utils.ImageDimensions;
import com.google.android.gms.vision.Frame;

/**
 * Wrapper around Frame allowing us to track Frame dimensions.
 * Tracking dimensions is used in StreamingFaceDetector and StreamingBarcodeDetector to provide painless FaceDetector/BarcodeDetector recreation
 * when image dimensions change.
 */

public class StreamingFrame {
  private Frame mFrame;
  private ImageDimensions mDimensions;

  public StreamingFrame(Frame frame, ImageDimensions dimensions) {
    mFrame = frame;
    mDimensions = dimensions;
  }

  public Frame getFrame() {
    return mFrame;
  }

  public ImageDimensions getDimensions() {
    return mDimensions;
  }
}

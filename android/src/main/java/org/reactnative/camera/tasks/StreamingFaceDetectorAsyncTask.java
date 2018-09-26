package org.reactnative.camera.tasks;

import android.util.SparseArray;

import com.google.android.gms.vision.face.Face;

import org.reactnative.facedetector.StreamingFaceDetector;
import org.reactnative.frame.StreamingFrame;
import org.reactnative.frame.StreamingFrameFactory;

public class StreamingFaceDetectorAsyncTask extends android.os.AsyncTask<Void, Void, SparseArray<Face>> {
  private byte[] mImageData;
  private int mWidth;
  private int mHeight;
  private int mRotation;
  private StreamingFaceDetector mFaceDetector;
  private StreamingFaceDetectorAsyncTaskDelegate mDelegate;

  public StreamingFaceDetectorAsyncTask(
      StreamingFaceDetectorAsyncTaskDelegate delegate,
      StreamingFaceDetector faceDetector,
      byte[] imageData,
      int width,
      int height,
      int rotation
  ) {
    mImageData = imageData;
    mWidth = width;
    mHeight = height;
    mRotation = rotation;
    mDelegate = delegate;
    mFaceDetector = faceDetector;
  }

  @Override
  protected SparseArray<Face> doInBackground(Void... ignored) {
    if (isCancelled() || mDelegate == null || mFaceDetector == null || !mFaceDetector.isOperational()) {
      return null;
    }

    StreamingFrame frame = StreamingFrameFactory.buildFrame(mImageData, mWidth, mHeight, mRotation);
    return mFaceDetector.detect(frame);
  }

  @Override
  protected void onPostExecute(SparseArray<Face> faces) {
    super.onPostExecute(faces);

    if (faces == null) {
      mDelegate.onFaceDetectionError(mFaceDetector);
    } else {
      if (faces.size() > 0) {
        mDelegate.onFacesDetected(faces, mWidth, mHeight, mRotation);
      }
      mDelegate.onFaceDetectingTaskCompleted();
    }
  }
}

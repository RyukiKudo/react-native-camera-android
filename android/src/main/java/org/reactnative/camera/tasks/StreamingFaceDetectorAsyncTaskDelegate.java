package org.reactnative.camera.tasks;

import android.util.SparseArray;

import com.google.android.gms.vision.face.Face;

import org.reactnative.facedetector.StreamingFaceDetector;

public interface StreamingFaceDetectorAsyncTaskDelegate {
  void onFacesDetected(SparseArray<Face> face, int sourceWidth, int sourceHeight, int sourceRotation);
  void onFaceDetectionError(StreamingFaceDetector faceDetector);
  void onFaceDetectingTaskCompleted();
}

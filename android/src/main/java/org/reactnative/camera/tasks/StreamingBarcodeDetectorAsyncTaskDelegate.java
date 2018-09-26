package org.reactnative.camera.tasks;

import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;

import org.reactnative.barcodedetector.StreamingBarcodeDetector;

public interface StreamingBarcodeDetectorAsyncTaskDelegate {

    void onBarcodesDetected(SparseArray<Barcode> barcodes, int sourceWidth, int sourceHeight, int sourceRotation);

    void onBarcodeDetectionError(StreamingBarcodeDetector barcodeDetector);

    void onBarcodeDetectingTaskCompleted();
}

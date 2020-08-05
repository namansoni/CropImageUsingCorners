package com.example.crop_image_using_corners;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import org.opencv.core.Size;

import java.io.ByteArrayOutputStream;

/** CropImageUsingCornersPlugin */
public class CropImageUsingCornersPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "crop_image_using_corners");
    channel.setMethodCallHandler(this);
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "crop_image_using_corners");
    channel.setMethodCallHandler(new CropImageUsingCornersPlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
    if(call.method.equals("cropImage")){
      if(OpenCVLoader.initDebug()){
        System.out.println("opencv working");
        Bitmap bitmap= BitmapFactory.decodeFile(call.argument("filePath").toString());
        int height=bitmap.getHeight();
        int width=bitmap.getWidth();
        double tl_x=call.argument("tl_x");
        double tl_y=call.argument("tl_y");
        double tr_x=call.argument("tr_x");
        double tr_y=call.argument("tr_y");
        double bl_x=call.argument("bl_x");
        double bl_y=call.argument("bl_y");
        double br_x=call.argument("br_x");
        double br_y=call.argument("br_y");
        Mat mat=new Mat();
        Utils.bitmapToMat(bitmap,mat);
        Mat src_mat=new Mat(4,1, CvType.CV_32FC2);
        Mat dst_mat=new Mat(4,1,CvType.CV_32FC2);
        src_mat.put(0,0,tl_x,tl_y,tr_x,tr_y,bl_x,bl_y,br_x,br_y);
        dst_mat.put(0,0,0.0,0.0,width,0.0, 0.0,height,width,height);
        Mat perspectiveTransform= Imgproc.getPerspectiveTransform(src_mat, dst_mat);

        Imgproc.warpPerspective(mat, mat, perspectiveTransform, new Size(width,height));
        Utils.matToBitmap(mat,bitmap);
        bitmap=Bitmap.createScaledBitmap(bitmap,2480,3508,true);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray=stream.toByteArray();
        result.success(byteArray);
      }
    }
    else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}

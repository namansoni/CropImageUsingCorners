import Flutter
import UIKit

public class SwiftCropImageUsingCornersPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "crop_image_using_corners", binaryMessenger: registrar.messenger())
    let instance = SwiftCropImageUsingCornersPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}

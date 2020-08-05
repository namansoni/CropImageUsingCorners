import 'dart:async';
import 'dart:io';

import 'package:crop_image_using_corners/crop_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class CropImageUsingCorners {
  static const MethodChannel _channel =
      const MethodChannel('crop_image_using_corners');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<dynamic>  CropImage(BuildContext context,File file) async{
    return Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => CropImage1(file),
    ));
  }

}

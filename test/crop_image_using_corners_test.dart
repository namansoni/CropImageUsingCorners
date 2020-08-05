import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:crop_image_using_corners/crop_image_using_corners.dart';

void main() {
  const MethodChannel channel = MethodChannel('crop_image_using_corners');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await CropImageUsingCorners.platformVersion, '42');
  });
}

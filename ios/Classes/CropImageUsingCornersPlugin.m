#import "CropImageUsingCornersPlugin.h"
#if __has_include(<crop_image_using_corners/crop_image_using_corners-Swift.h>)
#import <crop_image_using_corners/crop_image_using_corners-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "crop_image_using_corners-Swift.h"
#endif

@implementation CropImageUsingCornersPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftCropImageUsingCornersPlugin registerWithRegistrar:registrar];
}
@end

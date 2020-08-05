import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:crop_image_using_corners/crop_image_using_corners.dart';
import 'package:image_picker/image_picker.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    File file;
    var bytes;
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Button()
      ),
    );
  }
}
class Button extends StatefulWidget {
  @override
  _ButtonState createState() => _ButtonState();
}

class _ButtonState extends State<Button> {
  File file;
  var bytes;
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        RaisedButton(
          onPressed: ()async{
            file=await ImagePicker.pickImage(source: ImageSource.gallery);
            var byteArray= await CropImageUsingCorners.CropImage(context, file);
            setState(() {
              bytes=byteArray;
            });
          },
        ),
        bytes==null?Container():Image.memory(bytes)
      ],
    );
  }
}


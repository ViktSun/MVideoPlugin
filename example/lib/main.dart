import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_mvideo_plugin/flutter_mvideo_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var viewPlayerController;

  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
//    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
//  Future<void> initPlatformState() async {
//    String platformVersion;
//    // Platform messages may fail, so we use a try/catch PlatformException.
//    try {
//      platformVersion = await FlutterMvideoPlugin.platformVersion;
//    } on PlatformException {
//      platformVersion = 'Failed to get platform version.';
//    }
//
//    // If the widget was removed from the tree while the asynchronous platform
//    // message was in flight, we want to discard the reply rather than calling
//    // setState to update our non-existent appearance.
//    if (!mounted) return;
//
//    setState(() {
//      _platformVersion = platformVersion;
//    });
//  }

  @override
  Widget build(BuildContext context) {
    var x = 0.0;
    var y = 0.0;
    var width = 400.0;
    var height = width * 9.0 / 16.0;

    MVideoPlayer videoPlayer = MVideoPlayer(
        onCreated: _onPlayerViewCreate,
        x: x,
        y: y,
        width: width,
        height: height);

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Container(
          child: videoPlayer,
          width: width,
          height: height,
        ),
      ),
    );
  }

  void _onPlayerViewCreate(viewPlayerController) {
    this.viewPlayerController = viewPlayerController;
    this.viewPlayerController.loadUrl("http://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4");
  }
}

import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

typedef void MVideoPlayerCreatedCallBack(MVideoPlayerController controller);

//class FlutterMvideoPlugin {
//  static const MethodChannel _channel =
//      const MethodChannel('flutter_mvideo_plugin');
//
//  static Future<String> get platformVersion async {
//    final String version = await _channel.invokeMethod('getPlatformVersion');
//    return version;
//  }
//}

class MVideoPlayerController {
  MethodChannel _channel;

  MVideoPlayerController.init(int id) {
    _channel = MethodChannel("flutter_mvideo_plugin_$id");
  }

  ///暂停播放
  void onPause() {
    _channel.invokeListMethod('onPause');
  }

  ///继续播放
  void onResume() {
    _channel.invokeListMethod('onResume');
  }

  ///退出全屏
  Future<bool> quitFullScreen() async {
    return await _channel.invokeMethod<bool>('quitFullScreen');
  }

  ///传入视频地址
  Future<void> loadUrl(String url, String cover) async {
    assert(url != null);
    return _channel.invokeMethod('loadUrl', {"videoUrl": url, "cover": cover});
  }
}

class MVideoPlayer extends StatefulWidget {
  final MVideoPlayerCreatedCallBack onCreated;
  final x;
  final y;
  final width;
  final height;

  MVideoPlayer(
      {Key key,
      @required this.onCreated,
      @required this.x,
      @required this.y,
      @required this.width,
      @required this.height,});

  @override
  _MVideoPlayerState createState() => _MVideoPlayerState();
}

class _MVideoPlayerState extends State<MVideoPlayer> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.opaque,
      child: initPlayerView(),
    );
  }

  initPlayerView() {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'flutter_mvideo_plugin',
        onPlatformViewCreated: onPlatformViewCreated,
        creationParams: <String, dynamic>{
          "x": widget.x,
          "y": widget.y,
          "width": widget.width,
          "height": widget.height,
        },
        creationParamsCodec: const StandardMessageCodec(),
      );
    } else {
      return UiKitView(
        viewType: 'flutter_mvideo_plugin',
        onPlatformViewCreated: onPlatformViewCreated,
        creationParams: <String, dynamic>{
          "x": widget.x,
          "y": widget.y,
          "width": widget.width,
          "height": widget.height,
        },
        creationParamsCodec: const StandardMessageCodec(),
      );
    }
  }

  Future<void> onPlatformViewCreated(int id) async {
    if (widget.onCreated == null) {
      return;
    }
    widget.onCreated(MVideoPlayerController.init(id));
  }
}

# flutter_mvideo_plugin

[![Language](https://img.shields.io/badge/language-Dart%7CKotlin-orange.svg)](https://flutterchina.club/get-started/install/)
[![Platform](https://img.shields.io/badge/platform-android-orange.svg)](https://developer.android.com/)
![APi Version](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)
[![License](https://img.shields.io/github/license/ViktSun/MVideoPlugin.svg)](https://opensource.org/licenses/Apache-2.0)
[![Author](https://img.shields.io/badge/Author-viktsun-blue.svg)](http://www.sunwrite.top)

这主要是封装的flutter 播放器插件，具体效果可以看[flutter_mvp](https://github.com/ViktSun/flutter_mvp)

#主要功能

- 播放各种格式视频

- 横竖屏切换

- 生命周期的监听，退到后台暂停，前台继续播放等


#使用

在要使用播放器的项目中引入
```angular2html
flutter_mvideo_plugin:
git:
url: https://github.com/UrielSun/MVideoPlugin.git
```
具体参考[flutter_mvp](https://github.com/ViktSun/flutter_mvp)


#注意事项

- 本项目Android播放器插件开发为Androidx，compileSdkVersion 29，minSdkVersion 19
- 开发使用的语言为Kotlin
- 本项目中添加了对http的支持，在res文件下创建一个xml文件夹，创建network_security_config.xml文件

network_security_config内容如下

```angular2html
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

同时将文件配置到 AndroidManifest.xml 清单文件，application 节点中
```angular2html
<application
  android:networkSecurityConfig="@xml/network_security_config"
/>
```

# Thanks
[GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer) 易用的原生Android视频播放器

#License

```angular2html
Copyright 2019 viktsun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

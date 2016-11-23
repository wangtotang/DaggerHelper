# DaggerHelper:
## An android studio plugin to help you create dagger class easily

    用于自动生成Dagger Component和Module以及一些目录

## Installation

你可以下载[DaggerHelper.jar](https://github.com/wangtotang/DaggerHelper/raw/master/DaggerHelper.jar), 然后按照步骤进行安装: Preferences -> Plugin -> Install plugin from disk.

## Usage

#### 1.将dagger添加到classpath

```groovy
dependencies {
    //dagger2
    compile "com.google.dagger:dagger:${daggerVersion}"
    annotationProcessor  "com.google.dagger:dagger-compiler:${daggerVersion}"
}
```

#### 2.生成工程目录和基本类

在 `src/main/java/${你的包名}`下，鼠标右键 New -> Dagger Project,输入类前缀，点击ok即可生成

![Dagger Project](https://github.com/wangtotang/DaggerHelper/blob/master/images/dagger%20project.jpg)

#### 3.生成模块目录和Component/Module

在 `module`下，鼠标右键 New -> Dagger Module,输入类前缀，点击ok即可生成

![Dagger Module](https://github.com/wangtotang/DaggerHelper/blob/master/images/dagger%20module.jpg)

## Bugs and Feedback

如果有bugs,功能需求以及讨论，可以提issues,无论群上还是[GitHub Issues][issues].

## LICENSE

    Copyright 2016 the DaggerHelper author

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [issues]:https://github.com/wangtotang/DaggerHelper/issues

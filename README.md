# VerifyEditText
带下划线的验证码输入框

![screenshot](https://raw.githubusercontent.com/cirno-poi/VerifyEditText/master/picture/screenshot.gif)


## gradle使用：

- Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- 添加依赖：
```
	dependencies {
	        implementation 'com.github.cirno-poi:VerifyEditText:1.0.0'
	}
```

项目包含一个demo，可以直接down了运行。

- 可自定义输入框数量，下划线颜色和厚度，输入框间距，输入框光标（使用反射实现）；
- 支持全部下划线变色；
- 支持AOSP键盘的删除。

自定义属性如下：

|属性名|类型|说明|
|:---:|:---:|:---:|
|inputCount|integer|输入框数量|
|inputSpace|dimension|输入框间距|
|underlineHeight|dimension|下划线厚度|
|mTextSize|dimension|输入框文本大小|
|focusColor|color|下划线选中的颜色|
|defaultColor|color|下划线未选中的颜色|
|underlineSpace|dimension|下划线距文本的距离|
|cursorDrawable|reference|输入框光标|

# Hello World in Canvas

This is a small Katalon Studio project. This project proposes a solution to a question raised by a topic in the Katalon Community:

- https://forum.katalon.com/t/i-want-to-draw-a-signature-in-the-canvas-in-katalon-but-dont-know-how-to-do-can-anyone-please-help/171167

I made a HTML `page.html` that has an `<canvas>` element.

```
<!DOCTYPE html>
<html>
<body style="background-color: #eee">
    <h3>writing a text in Canvas ...</h3>
    <canvas id="myCanvas" width="360" height="100"
            style="border:1px solid #d3d3d3; background-color:white;">
        Your browser does not support the HTML canvas tag.</canvas>
</body>
</html>
```

When opened in a browser, the html looks like:

![initial](http://kazurayam.github.io/HelloWorldInCanvas/images/initial.png)

I wrote a Test Case script `TC1` as follows

```
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String id, String xpath) {
	TestObject to = new TestObject(id)
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}

WebUI.openBrowser('')
WebUI.setViewPortSize(800, 400)
File html = new File("./page.html")
WebUI.navigateToUrl(html.toURI().toURL().toExternalForm())
TestObject tObj = makeTestObject("myCanvas", "//canvas[@id='myCanvas']")
WebUI.verifyElementPresent(tObj, 10)

String script = '''
	let c = document.getElementById("myCanvas");
	let ctx = c.getContext("2d");
	ctx.font = "45px Georgia, serif";
	ctx.fillText("Hello World", 30, 60);
'''
WebUI.executeJavaScript(script, [])

WebUI.delay(3)
WebUI.closeBrowser()
```

The `TC1` injects a JavaScript code into the page open on a browser, and run the script.

Then the page turned to be as follows:

![text drawn](http://kazurayam.github.io/HelloWorldInCanvas/images/text_drawn.png)

# Conclusion

This demonstrates how to draw a text in a canvas element on a HTML page using Katalon Studio.

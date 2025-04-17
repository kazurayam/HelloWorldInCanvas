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

WebUI.delay(30)
WebUI.closeBrowser()
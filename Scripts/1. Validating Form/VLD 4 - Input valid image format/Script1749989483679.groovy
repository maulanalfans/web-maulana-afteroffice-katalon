import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration

WebUI.openBrowser('')
WebUI.comment('Upload image with invalid format this test case should success, but in web user can upload with invalid format')
WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.verifyElementPresent(findTestObject('Object Repository/Practice Form/Title Name - Practice Form'), 0)

WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - First name'), firstName)

WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - Last name'), lastName)

WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - User Email'), email)

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/radiobox - Male'), 0)
if (gender == 'Male') {
    WebUI.click(findTestObject('Object Repository/Practice Form/radiobox - Male'))
} else if (gender == 'Female') {
    WebUI.click(findTestObject('Object Repository/Practice Form/radiobox - Female'))
} else if (gender == 'Other') {
    WebUI.click(findTestObject('Object Repository/Practice Form/radiobox - Other'))
} else {
    KeywordUtil.markPassed('User not click the gender radio option')
}

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/txtfield - Mobile number'), 0)
WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - Mobile number'), mobile)

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/txtfield - Birth date'), 0)
WebUI.click(findTestObject('Object Repository/Practice Form/txtfield - Birth date'))
WebUI.click(findTestObject('Object Repository/Practice Form/specific date picker'))

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/txtfield - Subjects'), 0)
if(subjects != null) {
	WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - Subjects'), subjects)
	WebUI.sendKeys(findTestObject('Object Repository/Practice Form/txtfield - Subjects'), Keys.TAB.toString())
}else {
	WebUI.comment('data subjects is null')
}

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/checkbox - Sports'), 0)
if (hobbies == 'Sports') {
	WebUI.click(findTestObject('Object Repository/Practice Form/checkbox - Sports'))
} else if (hobbies == 'Reading') {
	WebUI.click(findTestObject('Object Repository/Practice Form/checkbox - Reading'))
} else if (hobbies == 'Music') {
	WebUI.click(findTestObject('Object Repository/Practice Form/checkbox - Music'))
} else if(hobbies == ''){
	KeywordUtil.markPassed('User not choosing hobbies section')
}

String imagePath = RunConfiguration.getProjectDir() + '/Include/image/gambarDummy.jpeg'
WebUI.uploadFile(findTestObject('Object Repository/Practice Form/file - Picture'), imagePath)

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/textarea - Current address'), 0)
WebUI.setText(findTestObject('Object Repository/Practice Form/textarea - Current address'), currentAddress)

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/txtfield - State'), 0)

if(state != null && state != '') {
	WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - State'), state)
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Practice Form/txtfield - State'), Keys.TAB.toString())
}else {
	WebUI.comment('data state is null')
}

if(city != null && city != '') {
	WebUI.setText(findTestObject('Object Repository/Practice Form/txtfield - City'), city)
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Practice Form/txtfield - City'), Keys.chord(Keys.ARROW_DOWN, Keys.ENTER))
}else {
	WebUI.comment('data city is null')
}

WebUI.scrollToElement(findTestObject('Object Repository/Practice Form/button - Submit'), 0)
WebUI.click(findTestObject('Object Repository/Practice Form/button - Submit'))

WebUI.executeJavaScript("document.getElementById('fixedban')?.remove();", null, FailureHandling.CONTINUE_ON_FAILURE)

boolean isModalOpen = WebUI.verifyElementPresent(findTestObject('Object Repository/Modal/txt - Modal title'), 2, FailureHandling.CONTINUE_ON_FAILURE)
if(isModalOpen) {
	KeywordUtil.markPassed("User cannot upload picture with invalid format file.")
}else {
	KeywordUtil.markFailed("User success upload picture with invalid format file.")
}
WebUI.closeBrowser()
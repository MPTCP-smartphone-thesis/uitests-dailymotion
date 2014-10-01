package dailymotion;

import utils.Utils;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {

	private static String ID_VIDEO_LIST = "com.dailymotion.dailymotion:id/channelListView";
	private static String ID_VIDEO_CARD = "com.dailymotion.dailymotion:id/rootLayout";

	public void testDemo() throws UiObjectNotFoundException {
		assertTrue("OOOOOpps",
 Utils.openApp(this, "Dailymotion",
				"com.dailymotion.dailymotion"));
		sleep(3000);
		UiScrollable list = Utils.getScrollableWithId(ID_VIDEO_LIST);

		if (!list.exists()) {
			getUiDevice().pressBack();
			list = Utils.getScrollableWithId(ID_VIDEO_LIST);
		}

		while(Utils.scrollForward(list)) {
			int nb_childs = Utils.getChildCount(list);
			
			for (int i = 0; i < nb_childs - 1; i++) {
				assertTrue("Cannot see video...",
						Utils.click(Utils.getObjectWithId(ID_VIDEO_CARD, i)));
				sleep(60000);
				getUiDevice().pressBack();
			}
		}
	}

}
package dailymotion;

import utils.Utils;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {

	private static final String ID_VIDEO_LIST = "com.dailymotion.dailymotion:id/channelListView";
	private static final String ID_SEARCH = "com.dailymotion.dailymotion:id/searchItem";
	private static final String ID_TEXT = "com.dailymotion.dailymotion:id/search_src_text";

	private static int MAX_VIDEOS = 3;
	private static final int TIME_VIDEO = 25000;

	private void goToThePlaylist() {
		UiScrollable list = Utils.getScrollableWithId(ID_VIDEO_LIST);

		if (!list.exists()) {
			getUiDevice().pressBack();
			list = Utils.getScrollableWithId(ID_VIDEO_LIST);
		}

		sleep(1000);
		Utils.click(Utils.getObjectWithDescription("Open menu"));
		sleep(1000);
		Utils.click(Utils.getObjectWithClassNameAndText("android.widget.TextView", "conscience-tranquille"));
		sleep(1000);
		Utils.click(new UiObject(new UiSelector().textContains("playlists")));
		sleep(1000);
		Utils.click(Utils.getObjectWithClassName(
				"android.widget.RelativeLayout", 7));
		sleep(1000);
	}

	private void seeVideos() {
		UiScrollable list = Utils.getScrollableWithId(ID_VIDEO_LIST);

		if (!list.exists()) {
			getUiDevice().pressBack();
			list = Utils.getScrollableWithId(ID_VIDEO_LIST);
		}

		int nVid = 0;
		while (nVid < MAX_VIDEOS && Utils.scrollForward(list)) {
			int nb_childs = Utils.getChildCount(list);

			for (int i = 0; nVid < MAX_VIDEOS && i < nb_childs - 1; i++, nVid++) {
				assertTrue("Cannot see video...", Utils.click(Utils
						.getObjectWithClassName(
								"android.widget.RelativeLayout", i)));
				sleep(TIME_VIDEO);
				getUiDevice().pressBack();
				Utils.scrollForward(list);
			}
		}
	}

	public void testDemo() throws UiObjectNotFoundException {
		assertTrue("OOOOOpps",
				Utils.openApp(this, "Dailymotion",
						"com.dailymotion.dailymotion",
						"com.dailymotion.dailymotion.activity.InterstitialActivity"));
		MAX_VIDEOS = Math.max(1, (int) (MAX_VIDEOS * Utils.getMultTime(this)));
		sleep(3000);
		goToThePlaylist();
		seeVideos();
	}
}

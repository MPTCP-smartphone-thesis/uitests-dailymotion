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

	private static int MAX_VIDEOS = 4;
	private static int TIME_VIDEO = 30000;

	private void goToThePlaylist() {
		UiScrollable list = Utils.getScrollableWithId(ID_VIDEO_LIST);

		if (!list.exists()) {
			getUiDevice().pressBack();
			list = Utils.getScrollableWithId(ID_VIDEO_LIST);
		}

		sleep(1000);
		Utils.click(Utils.getObjectWithId(ID_SEARCH));
		sleep(1000);
		Utils.setText(Utils.getObjectWithId(ID_TEXT), "conscience-tranquille");
		getUiDevice().pressEnter();
		sleep(1000);
		Utils.click(Utils.getObjectWithClassName(
				"android.widget.RelativeLayout", 0));
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
		while (nVid < 10 && Utils.scrollForward(list)) {
			int nb_childs = Utils.getChildCount(list);

			for (int i = 0; nVid < MAX_VIDEOS && i < nb_childs - 1; i++, nVid++) {
				assertTrue("Cannot see video...", Utils.click(Utils
						.getObjectWithClassName(
								"android.widget.RelativeLayout", i)));
				sleep(TIME_VIDEO);
				getUiDevice().pressBack();
			}
		}
	}

	public void testDemo() throws UiObjectNotFoundException {
		assertTrue("OOOOOpps",
			Utils.openApp(this, "Dailymotion", "com.dailymotion.dailymotion"));
		sleep(3000);
		String iface = getParams().getString("iface");
		if (iface != null) {
			Utils.launchTcpdump("dailymotion", iface);
		}
		goToThePlaylist();
		seeVideos();
		if (iface != null) {
			Utils.killTcpdump();
		}
	}
}

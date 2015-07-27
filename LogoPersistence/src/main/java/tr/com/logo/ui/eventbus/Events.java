package tr.com.logo.ui.eventbus;

public class Events {

	public static class ViewAlteredEvent {
		private String navigationState;

		public ViewAlteredEvent(String navigationState) {
			super();
			this.navigationState = navigationState;
		}

		public String getNavigationState() {
			return navigationState;
		}

	}
}

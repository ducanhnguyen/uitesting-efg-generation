package guitree.object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public abstract class AbstractGuiNode extends AbstractNode {

	private List<EfgEvent> events = new ArrayList<>();

	public List<EfgEvent> getEvents() {
		return events;
	}

	public void setEvents(List<EfgEvent> events) {
		this.events = events;
	}

	public abstract String getNameInXPath();

	public String getXPath() {
		String XPath = "";
		AbstractGuiNode currentNode = this;
		XPath = currentNode.getNameInXPath();
		while (!(currentNode.getParent() instanceof GuiStructureNode)) {
			currentNode = (AbstractGuiNode) currentNode.getParent();
			XPath = currentNode.getNameInXPath() + "/" + XPath;
		}
		return XPath;
	}
}

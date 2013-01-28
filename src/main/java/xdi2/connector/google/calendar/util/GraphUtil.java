package xdi2.connector.google.calendar.util;

import xdi2.connector.google.calendar.mapping.GoogleCalendarMapping;
import xdi2.core.ContextNode;
import xdi2.core.Graph;
import xdi2.core.Literal;
import xdi2.core.xri3.impl.XDI3Segment;
import xdi2.messaging.constants.XDIMessagingConstants;

public class GraphUtil {

	private GraphUtil() { }

	public static String retrieveAccessToken(Graph graph, XDI3Segment userXri) {

		XDI3Segment contextNodeXri = XDI3Segment.create("" + GoogleCalendarMapping.XRI_S_GOOGLE_CALENDAR_CONTEXT + userXri + XDIMessagingConstants.XRI_S_OAUTH_TOKEN);

		ContextNode contextNode = graph.findContextNode(contextNodeXri, false);
		if (contextNode == null) return null;

		Literal literal = contextNode.getLiteral();
		if (literal == null) return null;

		return literal.getLiteralData();
	}

	public static void storeAccessToken(Graph graph, XDI3Segment userXri, String accessToken) {

		XDI3Segment contextNodeXri = XDI3Segment.create("" + GoogleCalendarMapping.XRI_S_GOOGLE_CALENDAR_CONTEXT + userXri + XDIMessagingConstants.XRI_S_OAUTH_TOKEN);

		ContextNode contextNode = graph.findContextNode(contextNodeXri, true);

		if (contextNode.containsLiteral())
			contextNode.getLiteral().setLiteralData(accessToken);
		else
			contextNode.createLiteral(accessToken);
	}

	public static void removeAccessToken(Graph graph, XDI3Segment userXri) {

		XDI3Segment contextNodeXri = XDI3Segment.create("" + GoogleCalendarMapping.XRI_S_GOOGLE_CALENDAR_CONTEXT + userXri + XDIMessagingConstants.XRI_S_OAUTH_TOKEN);

		ContextNode contextNode = graph.findContextNode(contextNodeXri, false);
		if (contextNode == null) return;

		contextNode.delete();
	}
}

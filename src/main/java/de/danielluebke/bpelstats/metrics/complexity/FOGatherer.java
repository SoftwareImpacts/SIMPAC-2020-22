package de.danielluebke.bpelstats.metrics.complexity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Gatherer for calulating the Fan Out (FO)
 * 
 * @author dluebke
 * 
 */
public class FOGatherer extends BPELComplexityMetricGatherer {

	private int fo = 0;
	private String bpelNamespace;
	
	@Override
	public void startDocument() throws SAXException {
		fo = 0;
		bpelNamespace = null;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if(bpelNamespace == null) {
			bpelNamespace = uri;
		}
		
		if(bpelNamespace.equals(uri) && localName.equals("invoke")) {
			String operationName = attributes.getValue("operation").toLowerCase();
			if(!operationName.endsWith("callback") || !operationName.endsWith("failure")) {
				fo++;
			}
		}
	}

	@Override
	public String getName() {
		return "FO";
	}

	@Override
	public String getValue() {
		return "" + fo;
	}

}

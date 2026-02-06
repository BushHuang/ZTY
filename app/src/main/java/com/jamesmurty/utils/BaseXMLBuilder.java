package com.jamesmurty.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import net.iharder.Base64;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class BaseXMLBuilder {
    private static boolean isNamespaceAware = true;
    private Document xmlDocument;
    private Node xmlNode;

    protected BaseXMLBuilder(Document document) {
        this.xmlDocument = null;
        this.xmlNode = null;
        this.xmlDocument = document;
        this.xmlNode = document.getDocumentElement();
    }

    protected BaseXMLBuilder(Node node, Node node2) throws DOMException {
        this.xmlDocument = null;
        this.xmlNode = null;
        this.xmlNode = node;
        if (node instanceof Document) {
            this.xmlDocument = (Document) node;
        } else {
            this.xmlDocument = node.getOwnerDocument();
        }
        if (node2 != null) {
            node2.appendChild(node);
        }
    }

    protected static Document createDocumentImpl(String str, String str2) throws ParserConfigurationException, FactoryConfigurationError {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(isNamespaceAware);
        Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
        documentNewDocument.appendChild((str2 == null || str2.length() <= 0) ? documentNewDocument.createElement(str) : documentNewDocument.createElementNS(str2, str));
        return documentNewDocument;
    }

    protected static Document parseDocumentImpl(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(isNamespaceAware);
        return documentBuilderFactoryNewInstance.newDocumentBuilder().parse(inputSource);
    }

    public abstract BaseXMLBuilder a(String str, String str2);

    public String asString() throws TransformerException {
        Properties properties = new Properties();
        properties.put("omit-xml-declaration", "yes");
        return asString(properties);
    }

    public String asString(Properties properties) throws TransformerException, IllegalArgumentException {
        StringWriter stringWriter = new StringWriter();
        toWriter(stringWriter, properties);
        return stringWriter.toString();
    }

    protected void assertElementContainsNoOrWhitespaceOnlyTextNodes(Node node) {
        Node nodeItem;
        NodeList childNodes = node.getChildNodes();
        int i = 0;
        while (true) {
            if (i >= childNodes.getLength()) {
                nodeItem = null;
                break;
            }
            if (3 == childNodes.item(i).getNodeType()) {
                nodeItem = childNodes.item(i);
                if (nodeItem.getTextContent().replaceAll("\\s", "").length() > 0) {
                    break;
                }
            }
            i++;
        }
        if (nodeItem == null) {
            return;
        }
        throw new IllegalStateException("Cannot add sub-element to element <" + node.getNodeName() + "> that contains a Text node that isn't purely whitespace: " + nodeItem);
    }

    public abstract BaseXMLBuilder attr(String str, String str2);

    public abstract BaseXMLBuilder attribute(String str, String str2);

    protected void attributeImpl(String str, String str2) throws DOMException {
        Node node = this.xmlNode;
        if (node instanceof Element) {
            ((Element) node).setAttribute(str, str2);
            return;
        }
        throw new RuntimeException("Cannot add an attribute to non-Element underlying node: " + this.xmlNode);
    }

    protected NamespaceContextImpl buildDocumentNamespaceContext() {
        return new NamespaceContextImpl(this.xmlDocument.getDocumentElement());
    }

    public abstract BaseXMLBuilder c(String str);

    public abstract BaseXMLBuilder cdata(String str);

    public abstract BaseXMLBuilder cdata(byte[] bArr);

    protected void cdataImpl(String str) throws DOMException {
        this.xmlNode.appendChild(getDocument().createCDATASection(str));
    }

    protected void cdataImpl(byte[] bArr) throws DOMException {
        this.xmlNode.appendChild(getDocument().createCDATASection(Base64.encodeBytes(bArr)));
    }

    public abstract BaseXMLBuilder cmnt(String str);

    public abstract BaseXMLBuilder comment(String str);

    protected void commentImpl(String str) throws DOMException {
        this.xmlNode.appendChild(getDocument().createComment(str));
    }

    public abstract BaseXMLBuilder d(String str);

    public abstract BaseXMLBuilder d(byte[] bArr);

    public abstract BaseXMLBuilder data(String str);

    public abstract BaseXMLBuilder data(byte[] bArr);

    public abstract BaseXMLBuilder document();

    public abstract BaseXMLBuilder e(String str);

    public abstract BaseXMLBuilder elem(String str);

    public abstract BaseXMLBuilder element(String str);

    public abstract BaseXMLBuilder element(String str, String str2);

    public String elementAsString() throws TransformerException {
        Properties properties = new Properties();
        properties.put("omit-xml-declaration", "yes");
        return elementAsString(properties);
    }

    public String elementAsString(Properties properties) throws TransformerException, IllegalArgumentException {
        StringWriter stringWriter = new StringWriter();
        toWriter(false, stringWriter, properties);
        return stringWriter.toString();
    }

    public abstract BaseXMLBuilder elementBefore(String str);

    public abstract BaseXMLBuilder elementBefore(String str, String str2);

    protected Element elementBeforeImpl(String str) {
        return elementBeforeImpl(str, this.xmlNode.lookupNamespaceURI(getPrefixFromQualifiedName(str)));
    }

    protected Element elementBeforeImpl(String str, String str2) throws DOMException {
        Node parentNode = this.xmlNode.getParentNode();
        assertElementContainsNoOrWhitespaceOnlyTextNodes(parentNode);
        Element elementCreateElement = str2 == null ? getDocument().createElement(str) : getDocument().createElementNS(str2, str);
        parentNode.insertBefore(elementCreateElement, this.xmlNode);
        return elementCreateElement;
    }

    protected Element elementImpl(String str, String str2) {
        assertElementContainsNoOrWhitespaceOnlyTextNodes(this.xmlNode);
        return str2 == null ? getDocument().createElement(str) : getDocument().createElementNS(str2, str);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BaseXMLBuilder)) {
            return false;
        }
        BaseXMLBuilder baseXMLBuilder = (BaseXMLBuilder) obj;
        return this.xmlDocument.equals(baseXMLBuilder.getDocument()) && this.xmlNode.equals(baseXMLBuilder.getElement());
    }

    public Document getDocument() {
        return this.xmlDocument;
    }

    public Element getElement() {
        Node node = this.xmlNode;
        if (node instanceof Element) {
            return (Element) node;
        }
        return null;
    }

    protected String getPrefixFromQualifiedName(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public abstract BaseXMLBuilder i(String str, String str2);

    public abstract BaseXMLBuilder importXMLBuilder(BaseXMLBuilder baseXMLBuilder);

    protected void importXMLBuilderImpl(BaseXMLBuilder baseXMLBuilder) throws DOMException {
        assertElementContainsNoOrWhitespaceOnlyTextNodes(this.xmlNode);
        this.xmlNode.appendChild(getDocument().importNode(baseXMLBuilder.getDocument().getDocumentElement(), true));
    }

    public abstract BaseXMLBuilder insertInstruction(String str, String str2);

    protected void insertInstructionImpl(String str, String str2) {
        getDocument().insertBefore(getDocument().createProcessingInstruction(str, str2), this.xmlNode);
    }

    public abstract BaseXMLBuilder inst(String str, String str2);

    public abstract BaseXMLBuilder instruction(String str, String str2);

    protected void instructionImpl(String str, String str2) throws DOMException {
        this.xmlNode.appendChild(getDocument().createProcessingInstruction(str, str2));
    }

    protected String lookupNamespaceURIImpl(String str) {
        return this.xmlNode.lookupNamespaceURI(getPrefixFromQualifiedName(str));
    }

    public abstract BaseXMLBuilder namespace(String str);

    public abstract BaseXMLBuilder namespace(String str, String str2);

    protected void namespaceImpl(String str) throws DOMException {
        namespaceImpl(null, str);
    }

    protected void namespaceImpl(String str, String str2) throws DOMException {
        if (!(this.xmlNode instanceof Element)) {
            throw new RuntimeException("Cannot add an attribute to non-Element underlying node: " + this.xmlNode);
        }
        if (str == null || str.length() <= 0) {
            ((Element) this.xmlNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", str2);
            return;
        }
        ((Element) this.xmlNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + str, str2);
    }

    public abstract BaseXMLBuilder ns(String str);

    public abstract BaseXMLBuilder ns(String str, String str2);

    public abstract BaseXMLBuilder r(String str);

    public abstract BaseXMLBuilder ref(String str);

    public abstract BaseXMLBuilder reference(String str);

    protected void referenceImpl(String str) throws DOMException {
        this.xmlNode.appendChild(getDocument().createEntityReference(str));
    }

    public abstract BaseXMLBuilder root();

    public abstract BaseXMLBuilder stripWhitespaceOnlyTextNodes() throws XPathExpressionException;

    protected void stripWhitespaceOnlyTextNodesImpl() throws XPathExpressionException, DOMException {
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//text()[normalize-space(.) = '']").evaluate(getDocument(), XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeItem = nodeList.item(i);
            nodeItem.getParentNode().removeChild(nodeItem);
        }
    }

    public abstract BaseXMLBuilder t(String str);

    public abstract BaseXMLBuilder text(String str);

    public abstract BaseXMLBuilder text(String str, boolean z);

    protected void textImpl(String str, boolean z) throws DOMException {
        if (str == null) {
            throw new IllegalArgumentException("Illegal null text value");
        }
        if (z) {
            this.xmlNode.setTextContent(str);
        } else {
            this.xmlNode.appendChild(getDocument().createTextNode(str));
        }
    }

    public void toWriter(Writer writer, Properties properties) throws TransformerException, IllegalArgumentException {
        toWriter(true, writer, properties);
    }

    public void toWriter(boolean z, Writer writer, Properties properties) throws TransformerException, IllegalArgumentException {
        StreamResult streamResult = new StreamResult(writer);
        DOMSource dOMSource = z ? new DOMSource(getDocument()) : new DOMSource(getElement());
        Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
        if (properties != null) {
            for (Map.Entry entry : properties.entrySet()) {
                transformerNewTransformer.setOutputProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        transformerNewTransformer.transform(dOMSource, streamResult);
    }

    public abstract BaseXMLBuilder up();

    public abstract BaseXMLBuilder up(int i);

    protected Node upImpl(int i) {
        Node parentNode = this.xmlNode;
        for (int i2 = 0; parentNode.getParentNode() != null && i2 < i; i2++) {
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    public abstract BaseXMLBuilder xpathFind(String str) throws XPathExpressionException;

    public abstract BaseXMLBuilder xpathFind(String str, NamespaceContext namespaceContext) throws XPathExpressionException;

    protected Node xpathFindImpl(String str, NamespaceContext namespaceContext) throws XPathExpressionException {
        Node node = (Node) xpathQuery(str, XPathConstants.NODE, namespaceContext);
        if (node != null && node.getNodeType() == 1) {
            return node;
        }
        throw new XPathExpressionException("XPath expression \"" + str + "\" does not resolve to an Element in context " + this.xmlNode + ": " + node);
    }

    public Object xpathQuery(String str, QName qName) throws XPathExpressionException {
        return xpathQuery(str, qName, null);
    }

    public Object xpathQuery(String str, QName qName, NamespaceContext namespaceContext) throws XPathExpressionException {
        XPath xPathNewXPath = XPathFactory.newInstance().newXPath();
        if (namespaceContext != null) {
            xPathNewXPath.setNamespaceContext(namespaceContext);
        }
        try {
            return xPathNewXPath.compile(str).evaluate(this.xmlNode, qName);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}

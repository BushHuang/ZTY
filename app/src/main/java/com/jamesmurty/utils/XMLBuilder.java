package com.jamesmurty.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class XMLBuilder extends BaseXMLBuilder {
    protected XMLBuilder(Document document) {
        super(document);
    }

    protected XMLBuilder(Node node, Node node2) {
        super(node, node2);
    }

    public static XMLBuilder create(String str) throws ParserConfigurationException, FactoryConfigurationError {
        return create(str, null);
    }

    public static XMLBuilder create(String str, String str2) throws ParserConfigurationException, FactoryConfigurationError {
        return new XMLBuilder(createDocumentImpl(str, str2));
    }

    public static XMLBuilder parse(File file) throws ParserConfigurationException, SAXException, IOException {
        return parse(new InputSource(new FileReader(file)));
    }

    public static XMLBuilder parse(String str) throws ParserConfigurationException, SAXException, IOException {
        return parse(new InputSource(new StringReader(str)));
    }

    public static XMLBuilder parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
        return new XMLBuilder(parseDocumentImpl(inputSource));
    }

    @Override
    public XMLBuilder a(String str, String str2) {
        return attribute(str, str2);
    }

    @Override
    public XMLBuilder attr(String str, String str2) {
        return attribute(str, str2);
    }

    @Override
    public XMLBuilder attribute(String str, String str2) throws DOMException {
        super.attributeImpl(str, str2);
        return this;
    }

    @Override
    public XMLBuilder c(String str) {
        return comment(str);
    }

    @Override
    public XMLBuilder cdata(String str) throws DOMException {
        super.cdataImpl(str);
        return this;
    }

    @Override
    public XMLBuilder cdata(byte[] bArr) throws DOMException {
        super.cdataImpl(bArr);
        return this;
    }

    @Override
    public XMLBuilder cmnt(String str) {
        return comment(str);
    }

    @Override
    public XMLBuilder comment(String str) throws DOMException {
        super.commentImpl(str);
        return this;
    }

    @Override
    public XMLBuilder d(String str) {
        return cdata(str);
    }

    @Override
    public XMLBuilder d(byte[] bArr) {
        return cdata(bArr);
    }

    @Override
    public XMLBuilder data(String str) {
        return cdata(str);
    }

    @Override
    public XMLBuilder data(byte[] bArr) {
        return cdata(bArr);
    }

    @Override
    public XMLBuilder document() {
        return new XMLBuilder(getDocument(), null);
    }

    @Override
    public XMLBuilder e(String str) {
        return element(str);
    }

    @Override
    public XMLBuilder elem(String str) {
        return element(str);
    }

    @Override
    public XMLBuilder element(String str) {
        return element(str, super.lookupNamespaceURIImpl(str));
    }

    @Override
    public XMLBuilder element(String str, String str2) {
        return new XMLBuilder(super.elementImpl(str, str2), getElement());
    }

    @Override
    public XMLBuilder elementBefore(String str) {
        return new XMLBuilder(super.elementBeforeImpl(str), null);
    }

    @Override
    public XMLBuilder elementBefore(String str, String str2) {
        return new XMLBuilder(super.elementBeforeImpl(str, str2), null);
    }

    @Override
    public XMLBuilder i(String str, String str2) {
        return instruction(str, str2);
    }

    @Override
    public XMLBuilder importXMLBuilder(BaseXMLBuilder baseXMLBuilder) throws DOMException {
        super.importXMLBuilderImpl(baseXMLBuilder);
        return this;
    }

    @Override
    public XMLBuilder insertInstruction(String str, String str2) {
        super.insertInstructionImpl(str, str2);
        return this;
    }

    @Override
    public XMLBuilder inst(String str, String str2) {
        return instruction(str, str2);
    }

    @Override
    public XMLBuilder instruction(String str, String str2) throws DOMException {
        super.instructionImpl(str, str2);
        return this;
    }

    @Override
    public XMLBuilder namespace(String str) throws DOMException {
        namespace((String) null, str);
        return this;
    }

    @Override
    public XMLBuilder namespace(String str, String str2) throws DOMException {
        super.namespaceImpl(str, str2);
        return this;
    }

    @Override
    public XMLBuilder ns(String str) {
        return namespace(str);
    }

    @Override
    public XMLBuilder ns(String str, String str2) {
        return attribute(str, str2);
    }

    @Override
    public XMLBuilder r(String str) {
        return reference(str);
    }

    @Override
    public XMLBuilder ref(String str) {
        return reference(str);
    }

    @Override
    public XMLBuilder reference(String str) throws DOMException {
        super.referenceImpl(str);
        return this;
    }

    @Override
    public XMLBuilder root() {
        return new XMLBuilder(getDocument());
    }

    @Override
    public XMLBuilder stripWhitespaceOnlyTextNodes() throws XPathExpressionException, DOMException {
        super.stripWhitespaceOnlyTextNodesImpl();
        return this;
    }

    @Override
    public XMLBuilder t(String str) {
        return text(str);
    }

    @Override
    public XMLBuilder text(String str) {
        return text(str, false);
    }

    @Override
    public XMLBuilder text(String str, boolean z) throws DOMException {
        super.textImpl(str, z);
        return this;
    }

    @Override
    public XMLBuilder up() {
        return up(1);
    }

    @Override
    public XMLBuilder up(int i) {
        Node nodeUpImpl = super.upImpl(i);
        return nodeUpImpl instanceof Document ? new XMLBuilder((Document) nodeUpImpl) : new XMLBuilder(nodeUpImpl, null);
    }

    @Override
    public XMLBuilder xpathFind(String str) throws XPathExpressionException {
        return xpathFind(str, (NamespaceContext) null);
    }

    @Override
    public XMLBuilder xpathFind(String str, NamespaceContext namespaceContext) throws XPathExpressionException {
        return new XMLBuilder(super.xpathFindImpl(str, namespaceContext), null);
    }
}

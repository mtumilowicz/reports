package xml.writer;

import core.xml.writer.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import core.xml.builder.XmlElementBuilder;
import core.xml.builder.XmlDocumentBuilderChainImpl;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class ChainReportTypeXmlWriter extends XmlWriter {

    private final XmlElementBuilder elementBuilder;

    public ChainReportTypeXmlWriter(String firstNodeName) {
        super(new XmlDocumentBuilderChainImpl(firstNodeName));
        this.elementBuilder = super.getDocumentBuilder().getElementBuilder();
    }

    @Override
    public Document prepare() {
        Element node1 = elementBuilder.element("node1")
                                            .element("node1_1")
                                                .attribute("node_1_1_a1", "node_1_1_v1")
                                                .attribute("node_1_1_a2", "node_1_1_v2")
                                                .element("node1_1_2")
                                                    .attribute("node_1_2_a1", "node_1_2_v1")
                                                .up()
                                            .element("note1_2")
                                            .up()
                                        .element("node2")
                                    .build();
        getDocumentBuilder().addElement(node1);

        return getDocumentBuilder().getDocument();
    }

    public static void main(String[] args) {
        ChainReportTypeXmlWriter xmlWriter = new ChainReportTypeXmlWriter("node0");

        xmlWriter.print();
    }
}

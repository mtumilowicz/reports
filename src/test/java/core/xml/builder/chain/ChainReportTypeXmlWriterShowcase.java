package core.xml.builder.chain;

import core.xml.builder.XmlDocumentBuilderChainImpl;
import core.xml.writer.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by mtumilowicz on 2017-06-15.
 */
public class ChainReportTypeXmlWriterShowcase extends XmlWriter {

    ChainReportTypeXmlWriterShowcase(String rootName) {
        super(new XmlDocumentBuilderChainImpl(rootName));
    }

    @Override
    public Document prepare() {
        Element node1 = createElement("node1")
                                            .element("node1_1")
                                                .attribute("node1_1_a1", "node1_1_v1")
                                                .attribute("node1_1_a2", "node1_1_v2")
                                                .element("node1_1_2")
                                                    .attribute("node1_1_2_a1", "node1_1_2_v1")
                                                .up()
                                            .element("note1_2")
                                            .up()
                                        .element("node2")
                                    .build();
        addElement(node1);

        return getDocument();
    }

    public static void main(String[] args) {
        ChainReportTypeXmlWriterShowcase xmlWriter = new ChainReportTypeXmlWriterShowcase("node0");

        xmlWriter.print();
    }
}

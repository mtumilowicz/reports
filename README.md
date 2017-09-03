# reports
### project structure
```
├── reports/
    ├── output
    │    ├── pdf
    │    │    └── test.pdf
    │    └── xls
    │         └── test.xlsx
    ├── src
        ├── main
            ├── java
                ├── core
                    ├── builder
                    │   └── GenericBuilder
                    ├── bundle
                    │   └── BundleHandler
                    ├── constants
                    │   └── CoreConstants
                    ├── date
                    │   └── CoreDateUtils
                    ├── dom
                    │   ├── DomDocumentWriter
                    │   └── StaticDomDocumentBuilderFactory
                    ├── entity
                    │   └── XmlEntity
                    ├── pdf
                    │   ├── builder
                    │   │   ├── ImageBuilder
                    │   │   └── PdfCellBuilder
                    │   ├── utils
                    │   │   ├── PdfFontsContainer
                    │   │   └── PdfFontsFactory
                    │   └── writer
                    │       └── AbstractDocumentWriter
                    ├── writer
                    │   └── DocumentWriter
                    ├── xlsx
                    │   ├── format
                    │   │   ├── XlsxDataFormat
                    │   │   └── XlsxDataFormatType
                    │   └── writer
                    │        └── AbstractXlsxWriter
                    └── xml
                        ├── builder
                        │   ├── BaseXmlDocumentBuilderImpl
                        │   ├── XmlDocumentBuilder
                        │   ├── XmlDocumentBuilderChainImpl
                        │   ├── XmlDocumentBuilderStraightImpl
                        │   └── XmlElementBuilder
                        ├── converter
                        │   └── DateOnlyConverter
                        ├── parser
                        │   └── XmlFromFile
                        ├── schema
                        │   └── XmlSchemaFactory
                        ├── transformer
                        │   └── XmlTransformer
                        ├── validator
                        │   ├── XmlValidatorFactory
                        │   └── XmlValidatorWrapper
                        └── writer
                            └── XmlWriter
```
# reports
### project structure
```
├── reports/
    ├── output
    │    ├── pdf
    │    │    └── test.pdf
    │    └── xls
    │         └── test.xlsx
    └── src
        ├── main
        │    └── java
        │        └── core
        │            ├── builder
        │            │   └── GenericBuilder
        │            ├── bundle
        │            │   └── BundleHandler
        │            ├── constants
        │            │   └── CoreConstants
        │            ├── date
        │            │   └── CoreDateUtils
        │            ├── dom
        │            │   ├── DomDocumentWriter
        │            │   └── StaticDomDocumentBuilderFactory
        │            ├── entity
        │            │   └── XmlEntity
        │            ├── pdf
        │            │   ├── builder
        │            │   │   ├── ImageBuilder
        │            │   │   └── PdfCellBuilder
        │            │   ├── utils
        │            │   │   ├── PdfFontsContainer
        │            │   │   └── PdfFontsFactory
        │            │   └── writer
        │            │       └── AbstractDocumentWriter
        │            ├── writer
        │            │   └── DocumentWriter
        │            ├── xlsx
        │            │   ├── format
        │            │   │   ├── XlsxDataFormat
        │            │   │   └── XlsxDataFormatType
        │            │   └── writer
        │            │        └── AbstractXlsxWriter
        │            └── xml
        │                ├── builder
        │                │   ├── BaseXmlDocumentBuilderImpl
        │                │   ├── XmlDocumentBuilder
        │                │   ├── XmlDocumentBuilderChainImpl
        │                │   ├── XmlDocumentBuilderStraightImpl
        │                │   └── XmlElementBuilder
        │                ├── converter
        │                │   └── DateOnlyConverter
        │                ├── parser
        │                │   └── XmlFromFile
        │                ├── schema
        │                │   └── XmlSchemaFactory
        │                ├── transformer
        │                │   └── XmlTransformer
        │                ├── validator
        │                │   ├── XmlValidatorFactory
        │                │   └── XmlValidatorWrapper
        │                └── writer
        │                    └── XmlWriter
        └── test
             └── java
                 ├── core
                 │   ├── bundle
                 │   ├── dom
                 │   └── xml
                 │       ├── builder
                 │       │   ├── chain
                 │       │   │     ├── ChainReportTypeXmlWriterShowcase
                 │       │   │     └── XmlDocumentBuilderChainImplTest
                 │       │   └── straight
                 │       │         ├── StraightReportTypeXmlWriterShowcase
                 │       │         └── XmlDocumentBuilderStraightImpl                 
                 │       ├── converter
                 │       │   └── DateConverterTest
                 │       └── parser
                 │           └── XmlFromFileTest
                 ├── dao
                 │   └── BookDAOMock
                 ├── database
                 │   └── DatabaseMock
                 ├── entity
                 │   ├── Book
                 │   ├── BookTest
                 │   └── BookType
                 ├── pdf
                 │   └── PdfGenerationTest
                 └── xlsx
                     └── XlsxGenerationTest
```
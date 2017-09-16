# reports
This project has 3 APIs:

* [PDF](#PDF) - cells and images builder, fonts handler, report files 
creator
* [XLS](#XLS) - xls cell formats handler, report files creator
* [XML](#XML) - two types of xml builders, xml to entity converter 
(using x-stream), entity to xml converter (using x-stream), 
file parser, schema factory, validation by scheme, dom-document writer

## project structure
```
.
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
    │            │   │   ├── cell
    │            │   │   │   ├── CellBackgroundColor
    │            │   │   │   ├── CellBorder
    │            │   │   │   ├── CellDefaults
    │            │   │   │   ├── CellText
    │            │   │   │   └── PdfCellBuilder
    │            │   │   ├── image
    │            │   │   │   └── PdfCellBuilder
    │            │   ├── utils
    │            │   │   ├── PdfFontsContainer
    │            │   │   └── PdfFontsFactory
    │            │   └── writer
    │            │       ├── AbstractDocumentWriter
    │            │       ├── InsertablePdfImage
    │            │       └── InsertablePdfTable
    │            ├── writer
    │            │   └── DocumentWriter
    │            ├── xlsx
    │            │   ├── format
    │            │   │   ├── XlsxDataFormat
    │            │   │   └── XlsxDataFormatType
    │            │   └── writer
    │            │       ├── AbstractXlsxWriter
    │            │       ├── InsertableXlsContent
    │            │       └── InsertableXlsSheet
    │            └── xml
    │                ├── builder
    │                │   ├── BaseXmlDocumentBuilderImpl
    │                │   ├── XmlDocumentBuilder
    │                │   ├── XmlDocumentBuilderChainImpl
    │                │   ├── XmlDocumentBuilderStraightImpl
    │                │   └── XmlElementBuilder
    │                ├── converter
    │                │   ├── DateOnlyConverter
    │                │   └── EntityToXmlConverter
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
             │   │   └── BundleHandlerTest
             │   ├── dom
             │   │   ├── ControlXmlDocument
             │   │   └── StaticDomDocumentBuilderFactoryTest
             │   └── xml
             │       ├── builder
             │       │   ├── chain
             │       │   │   ├── ChainReportTypeXmlWriterShowcase
             │       │   │   └── XmlDocumentBuilderChainImplTest
             │       │   └── straight
             │       │       ├── StraightReportTypeXmlWriterShowcase
             │       │       └── XmlDocumentBuilderStraightImpl                 
             │       ├── converter
             │       │   └── DateConverterTest
             │       ├── parser
             │       │   └── XmlFromFileTest
             │       └── parser
             │           └── XmlValidatorWrapperTest
             ├── dao
             │   └── BookDAOMock
             ├── database
             │   └── DatabaseMock
             ├── entity
             │   ├── Book
             │   ├── BookType
             │   └── EntityToXmlConverterTest
             ├── pdf
             │   ├── books
             │   │   ├── BooksCollectionTable
             │   │   ├── HarvardEmblem
             │   │   ├── PdfGenerationTest
             │   │   ├── ReportHeader
             │   │   ├── SpacingTable
             │   │   ├── SummaryBooksCollectionTable
             └── xlsx
                 └── books
                     ├── sheet
                     │   ├── first
                     │   │   ├── BookCollectionSheet
                     │   │   ├── BookCollectionSheetContent
                     │   │   ├── BookCollectionSheetTitle
                     │   │   └── BookCollectionTable
                     │   └── second
                     │       ├── SummarySheet
                     │       ├── SummarySheetContent
                     │       ├── SummarySheetTitle
                     │       └── SummaryTable
                     └── XlsxGenerationTest
```
## description
<a name="PDF"></a>
### PDF
* PdfCellBuilder: CellBackgroundColor, CellBorder, CellDefaults, CellText
* ImageBuilder
* PdfFontsContainer
* PdfFontsFactory
* AbstractDocumentWriter
* InsertablePdfImage
* InsertablePdfTable

<a name="XLS"></a>
### XLS
* XlsxDataFormat
* XlsxDataFormatType
* AbstractXlsxWriter
* InsertableXlsContent
* InsertableXlsSheet

<a name="XML"></a>
### XML
* BaseXmlDocumentBuilderImpl
* XmlDocumentBuilder
* XmlDocumentBuilderChainImpl
* XmlDocumentBuilderStraightImpl
* XmlElementBuilder
* DateOnlyConverter
* EntityToXmlConverter
* XmlFromFile
* XmlSchemaFactory
* XmlTransformer
* XmlValidatorFactory
* XmlValidatorWrapper
* XmlWriter

## examples

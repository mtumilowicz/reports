# reports
This project has 3 APIs:

* [PDF](#pdf) - cells and images builder, fonts handler, report files 
creator
* [XLS](#xls) - xls cell formats handler, report files creator
* [XML](#xml) - two types of xml builders, xml to entity converter 
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
    │            │   │   │   └── ImageBuilder
    │            │   ├── utils
    │            │   │   ├── PdfFontsContainer
    │            │   │   └── PdfFontsFactory
    │            │   └── writer
    │            │       ├── AbstractPdfWriter
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
<a name="pdf"></a>
PDF
---
* _PdfCellBuilder: CellBackgroundColor, CellBorder, CellDefaults, 
CellText_ - facilitates composing of pdf cells by chaining methods and
handling basic features (e.x. borders)
* _ImageBuilder_ - facilitates inserting images into pdf by setting size
and position in one go
* _PdfFontsContainer_ - it is actually a map containing fonts (cache);
* _PdfFontsContainer_ - produces embedded fonts in encoding Cp1250
* _AbstractPdfWriter_ - base class used in pdf file generation
* _InsertablePdfImage_ - interface used for inserting images
* _InsertablePdfTable_ - base class used in building tables (inserted
into pdf)

<a name="xls"></a>
XLS
---
* XlsxDataFormat
* XlsxDataFormatType
* AbstractXlsxWriter
* InsertableXlsContent
* InsertableXlsSheet

<a name="xml"></a>
XML
---
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

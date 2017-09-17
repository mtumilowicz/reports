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
handling basic features (eq. borders)
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
* _XlsxDataFormat_ - facilitates handling with different column date 
formats during files generation
* _XlsxDataFormatType_ - cache of format types (eg. #.00 - money,
YYYY-MM-DD hh:mm - date with time)
* _AbstractXlsxWriter_ - base class used in xls file generation
* _InsertableXlsContent_ - base class used in building content of sheets
* _InsertableXlsSheet_ - base class used in building sheets

<a name="xml"></a>
XML
---
* _BaseXmlDocumentBuilderImpl_ - consequence of two different approach 
of composing xml: chain and straight (common abstractions is extracted 
to that class)
* _XmlDocumentBuilder_ - interface for xml document builder
* _XmlDocumentBuilderChainImpl_ - implementation that allows to chain 
methods
* _XmlDocumentBuilderStraightImpl_ - orthodox implementation (every 
element has to be created and then added)
* _XmlElementBuilder_ - interface for element builder
* _DateOnlyConverter_ - exemplary x-stream converter
* _EntityToXmlConverter_ - wrapper for x-stream conversion from entity 
to xml
* _XmlFromFile_ - parse xml from file to a specific class
* _XmlSchemaFactory_ - loads xml schema from file (given path + 
language)
* _XmlTransformer_ - couple useful methods for xml transforming, for 
example: dom-document to string (using specific output format)
* _XmlValidatorFactory_ - javax.xml.validation.Validator factory
* _XmlValidatorWrapper_ - wrapper for xml validation (using schema)
* _XmlWriter_ - base class used in xml file generation

## examples
Example of using api (and references to more in test package).

PDF
---
1) **PdfCellBuilder** - we don't use this class directly but as a integral 
part of InsertablePdfTable.

_Methods description:_  
* _value(XXX value)_ - used to set value of type XXX 
(eg. String, BigDecimal, Date, Integer)  
```
getCellBuilder().value(value).build();
```

* _bold(), textAlignment(TextAlignment textAlignment), center(), 
right()_ 
```
getCellBuilder().value(XXX).center().bold().build();
getCellBuilder().value(XXX).right().build();
getCellBuilder().value(XXX).textAlignment(TextAlignment.LEFT).build();
```

* _singleCellFontSize(int fontSize), 
backgroundColor(Color backgroundColor)_ - changes only in the cell we are
working on (without any influence on the others)
```
getCellBuilder().value(value).singleCellFontSize(20).build()
getCellBuilder().value(value).backgroundColor(Color.CYAN).build()
```

* _setDefaultFontSize(int defaultFontSize), 
setDefaultBackgroundColor(Color defaultBackgroundColor),
setDefaultStyle(Style style)_ - changes permanently all cell constructed
by instance of PdfCellBuilder (still can be outshouted by using 
singleCellFontSize and so on...); changes are saved in CellDefaults;
methods don't allow chaining
```
getCellBuilder().setDefaultFontSize(20);
// constructing cells
```

* _build()_ - after calling this method we construct cell with all set
features then reset all fields to default, eg. CellBorder.border field
is set to true;
```
public Cell build() {
    Cell cell = prepareCell();

    resetFields();

    return cell;
    }
    
private void resetFields() {
    this.cellText = new CellText();
    this.cellBackgroundColor = new CellBackgroundColor();
    this.cellBorder = new CellBorder();
}
```
more exemplary code of usages *PdfCellBuilder* in classes (test package)
: _ReportHeader, SummaryBooksCollectionTable, BooksCollectionTable_

XLS
---

XML
---
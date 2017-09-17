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
             │   │   └── SummaryBooksCollectionTable
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
1. **PdfCellBuilder** - we don't use this class directly but as a 
integral part of _InsertablePdfTable_.

_value(XXX value)_ - used to set value of type XXX 
(eg. String, BigDecimal, Date, Integer)  
```
getCellBuilder().value(value).build();
```

_bold(), textAlignment(TextAlignment textAlignment), center(), 
right()_ 
```
getCellBuilder().value(XXX).center().bold().build();
getCellBuilder().value(XXX).right().build();
getCellBuilder().value(XXX).textAlignment(TextAlignment.LEFT).build();
```

_singleCellFontSize(int fontSize), 
backgroundColor(Color backgroundColor)_ - changes only in the cell we are
working on (without any influence on the others)
```
getCellBuilder().value(value).singleCellFontSize(20).build()
getCellBuilder().value(value).backgroundColor(Color.CYAN).build()
```

_setDefaultFontSize(int defaultFontSize), 
setDefaultBackgroundColor(Color defaultBackgroundColor),
setDefaultStyle(Style style)_ - changes permanently all cell constructed
by instance of PdfCellBuilder (still can be outshouted by using 
singleCellFontSize and so on...); changes are saved in CellDefaults;
methods don't allow chaining
```
getCellBuilder().setDefaultFontSize(20);
// constructing cells
```

_build()_ - after calling this method we construct cell with all set
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
2. **ImageBuilder** - we use this class directly
```
ImageBuilder.Factory.get(path)
                .widthAndHeight(100, 100)
                .position(100, 100)
                .build();
```
width and height we give in floats, position is set in order: firstly
abscissa then ordinate (also floats).  
When we have reference to the dom document, we could use it like that 
(top left corner):
```
ImageBuilder.Factory.get("src/main/resources/harvard.png")
                .widthAndHeight(100, 100)
                .position(document.getLeftMargin(),
                        PageSize.A4.rotate().getHeight() - document.getTopMargin() - 100)
                .build();
```
more exemplary code of usages *ImageBuilder* in class (test package)
: _HarvardEmblem_  
3. **PdfFontsContainer** - we don't have direct access to the cache map, if
we want a new font, we have to declare method (as shown with helvetica),
because number of fonts used in application should be as less as possible
and strictly restricted:
```
public static PdfFont getHelvetica() {
    return get(FontConstants.HELVETICA);
}
```
more exemplary code of usages *PdfFontsContainer* in class (test package)
: _CellDefaults_  
4. **PdfFontsFactory** - produces embedded fonts in _Cp1250_ encoding for
_PdfFontsContainer_  
5. **AbstractPdfWriter** - base class for creating pdf file; usage:
```
XXX extends AbstractPdfWriter
```
then we _@Override prepare(Document document)_, where we construct 
document. We could use PdfDocumentBuilder to facilitate this activity 
(for more info go to pt. 6.).
```
protected void prepare(Document document) {
    Table table = new Table(new float[]{1});
    table.setDocument(document);
    table.setWidthPercent(100)
          .addHeaderCell(new PdfCellBuilder().value("bomba").build())
          .complete();
    }
```
more exemplary code of usages *AbstractPdfWriter* in class (test package)
: _PdfGenerationTest_  
6. **PdfDocumentBuilder** - facilitates creating pdf documents, by 
allowing chaining methods:  
_add(InsertablePdfImage image)_ - for adding image (wrapped in the 
_InsertablePdfImage_; more info in p. 7.)  
_add(InsertablePdfTable table)_ - for adding tables (wrapped in the 
_InsertablePdfTable_; more info in p. 8.)  
more exemplary code of usages *PdfDocumentBuilder* in class (test package)
: _PdfGenerationTest_  
7. **InsertablePdfImage** - every image that is inserted into report 
should be defined in separate class implementing _InsertablePdfImage_:
```
XXX implements InsertablePdfImage
```
then we have to simply _@Override_ method 
_Image getScaledFor(Document document)_
```
@Override
public Image getScaledFor(Document document) {
    return ImageBuilder.Factory.get("src/main/resources/harvard.png")
            .widthAndHeight(100, 100)
            .position(document.getLeftMargin(),
                    PageSize.A4.rotate().getHeight() - document.getTopMargin() - 100)
            .build();
}
```
more exemplary code of usages *InsertablePdfImage* in class (test package)
: _HarvardEmblem_  
8. **InsertablePdfTable** - every table that is inserted into report 
should be defined in separate class extending _InsertablePdfTable_:
```
XXX extends InsertablePdfTable
```
then we have to only _@Override_ method _Table get()_ (we have access 
to _PdfCellBuilder_ by _getCellBuilder()_, and _BundleHandler_ by 
_getBundles()_):
```
@Override
public Table get() {
    Table table = new Table(new float[]{1});
    table.setWidthPercent(100)
            .addHeaderCell(
                    getCellBuilder().value(getBundles().get(key)).build());
    
    return table;
}
```
more exemplary code of usages *InsertablePdfTable* in class (test package)
: _SummaryBooksCollectionTable, SpacingTable, ReportHeader, 
BooksCollectionTable_  

XLS
---

XML
---
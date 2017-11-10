# reports
This project has 3 APIs:

* [PDF](#pdf) - cells and images builder, fonts handler, report files 
creator
* [XLS](#xls) - cells builder, report files creator
* [XML](#xml) - two types of xml builders, xml to entity converter 
(using x-stream), entity to xml converter (using x-stream), file parser, 
schema factory, validation by scheme, dom-document writer, searching by 
xpath

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
    │            │       ├── InsertablePdfTable
    │            │       └── PdfDocumentBuilder
    │            ├── writer
    │            │   └── DocumentWriter
    │            ├── xlsx
    │            │   ├── builder
    │            │   │   └── cell
    │            │   │       ├── CellBorder
    │            │   │       ├── CellDefaults
    │            │   │       ├── CellForegroundColor
    │            │   │       ├── CellFormat
    │            │   │       ├── CellText
    │            │   │       ├── CustomCellStyle
    │            │   │       └── XlsxCellBuilder
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
    │                ├── writer
    │                │   └── XmlWriter
    │                └── xpath
    │                    └── XpathSearcher
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
             │       ├── validator
             │       │   └── XmlValidatorWrapperTest
             │       └── xpath
             │           └── XpathSearcherTest
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
             │   └── builder
             │       └── cell
             │           └── PdfCellBuilderTest
             └── xlsx
                 ├── books
                 │   ├── sheet
                 │   │   ├── first
                 │   │   │   ├── BookCollectionSheet
                 │   │   │   ├── BookCollectionSheetContent
                 │   │   │   ├── BookCollectionSheetTitle
                 │   │   │   └── BookCollectionTable
                 │   │   └── second
                 │   │       ├── SummarySheet
                 │   │       ├── SummarySheetContent
                 │   │       ├── SummarySheetTitle
                 │   │       └── SummaryTable
                 │   └── XlsxGenerationTest
                 └── builder
                     └── cell
                         └── XlsxCellBuilderTest

```
## description
<a name="pdf"></a>
PDF
---
* `PdfCellBuilder`: `CellBackgroundColor`, `CellBorder`, `CellDefaults`, 
`CellText` - facilitates composing of pdf cells by chaining methods and
handling basic features (eq. borders)
* `ImageBuilder` - facilitates inserting images into pdf by setting size
and position in one go
* `PdfFontsContainer` - it is actually a map containing fonts (cache);
* `PdfFontsFactory` - produces embedded fonts in encoding `Cp1250`
* `AbstractPdfWriter` - base class used in pdf file generation
* `InsertablePdfImage` - interface used for inserting images
* `InsertablePdfTable` - base class used in building tables (inserted
into pdf)
* `PdfDocumentBuilder` - util class used in composing pdf files

<a name="xls"></a>
XLS
---
* `XlsxCellBuilder`: `CellBorder`, `CellDefaults`, `CellForegroundColor`, 
`CellFormat`, `CellText`, `CustomCellStyle` - facilitates composing of 
xls cells by chaining methods and handling basic features (eq. borders)
* `XlsxDataFormat` - facilitates handling different column date formats 
during files generation
* `XlsxDataFormatType` - cache of format types (eg. `0.00` - money,
`YYYY-MM-DD hh:mm` - date with time)
* `AbstractXlsxWriter` - base class used in xls file generation
* `InsertableXlsContent` - base class used in building content of sheets
* `InsertableXlsSheet` - base class used in building sheets

<a name="xml"></a>
XML
---
* `BaseXmlDocumentBuilderImpl` - consequence of two different approach 
of composing xml: chain and straight (common abstractions is extracted 
to that class)
* `XmlDocumentBuilder` - interface for xml document builder
* `XmlDocumentBuilderChainImpl` - implementation that allows to chain 
methods
* `XmlDocumentBuilderStraightImpl` - orthodox implementation (every 
element has to be created and then added)
* `XmlElementBuilder` - interface for element builder
* `DateOnlyConverter` - exemplary x-stream converter
* `EntityToXmlConverter` - wrapper for x-stream conversion from entity 
to xml
* `XmlFromFile` - parse xml from file to a given class
* `XmlSchemaFactory` - loads xml schema from file (given path + 
language)
* `XmlTransformer` - couple useful methods for xml transforming, for 
example: dom-document to string (using specific output format)
* `XmlValidatorFactory` - javax.xml.validation.Validator factory
* `XmlValidatorWrapper` - wrapper for xml validation (using schema)
* `XmlWriter` - base class used in xml file generation
* `XpathSearcher` - enables searching documents by xpath

## examples
Example of using api (and references to more in test package).

## PDF
1) **PdfCellBuilder** - we don't use this class directly but as a 
integral part of `InsertablePdfTable`.

`value(XXX value)` - used to set value of type XXX 
(eg. String, BigDecimal, Date, Integer)  
```
getCellBuilder().value(value).build();
```

`bold()`, `textAlignment(TextAlignment textAlignment)`, `center()`, 
`right()` 
```
getCellBuilder().value(XXX).center().bold().build();
getCellBuilder().value(XXX).right().build();
getCellBuilder().value(XXX).textAlignment(TextAlignment.LEFT).build();
```

`singleCellFontSize(int fontSize)`, 
`backgroundColor(Color backgroundColor)` - changes only in the cell we are
working on (without any influence on the others)
```
getCellBuilder().value(value).singleCellFontSize(20).build()
getCellBuilder().value(value).backgroundColor(Color.CYAN).build()
```

`setDefaultFontSize(int defaultFontSize)`, 
`setDefaultBackgroundColor(Color defaultBackgroundColor)`,
`setDefaultStyle(Style style)` - changes permanently all cells constructed
by instance of `PdfCellBuilder` (still can be outshouted by using 
`singleCellFontSize` and so on...); changes are saved in `CellDefaults`;
methods don't allow chaining
```
getCellBuilder().setDefaultFontSize(20);
// constructing cells
```

`build()` - after calling this method we construct cell with all set
features then reset all fields to default, eg. `CellBorder.border` field
is set to `true`;
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
more exemplary code of usages `PdfCellBuilder` in classes (test package)
: `ReportHeader`, `SummaryBooksCollectionTable`, `BooksCollectionTable`
  
---
2) **ImageBuilder** - we use this class directly
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
more exemplary code of usages `ImageBuilder` in class (test package)
: `HarvardEmblem`  

---
3) **PdfFontsContainer** - we don't have direct access to the cache map, if
we want a new font, we have to declare method (as shown with helvetica),
because number of fonts used in application should be as less as possible
and strictly restricted:
```
public static PdfFont getHelvetica() {
    return get(FontConstants.HELVETICA);
}
```
more exemplary code of usages `PdfFontsContainer` in class (test package)
: `CellDefaults`  

---
4) **PdfFontsFactory** - produces embedded fonts in `Cp1250` encoding for
`PdfFontsContainer`  

---
5) **AbstractPdfWriter** - base class for creating pdf file; usage:
```
XXX extends AbstractPdfWriter
```
then we `@Override` method `prepare(Document document)`, where we construct 
document. We could use `PdfDocumentBuilder` to facilitate this activity 
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
more exemplary code of usages `AbstractPdfWriter` in class (test package)
: `PdfGenerationTest`  

---
6) **PdfDocumentBuilder** - facilitates creating pdf documents, by 
allowing chaining methods:  
`add(InsertablePdfImage image)` - for adding image (wrapped in the 
`InsertablePdfImage`; more info in p. 7.)  
`add(InsertablePdfTable table)` - for adding tables (wrapped in the 
`InsertablePdfTable`; more info in p. 8.)  
more exemplary code of usages `PdfDocumentBuilder` in class (test package)
: `PdfGenerationTest`  

---
7) **InsertablePdfImage** - every image that is inserted into report 
should be defined in separate class implementing `InsertablePdfImage`:
```
XXX implements InsertablePdfImage
```
then we have to simply `@Override` method 
`Image getScaledFor(Document document)`
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
more exemplary code of usages `InsertablePdfImage` in class (test package)
: `HarvardEmblem`  

---
8) **InsertablePdfTable** - every table that is inserted into report 
should be defined in separate class extending `InsertablePdfTable`:
```
XXX extends InsertablePdfTable
```
then we have to only `@Override` method `Table get()` (we have access 
to `PdfCellBuilder` by `getCellBuilder()`, and `BundleHandler` by 
`getBundles()`):
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
more exemplary code of usages `InsertablePdfTable` in class (test package)
: `SummaryBooksCollectionTable`, `SpacingTable`, `ReportHeader`, 
`BooksCollectionTable`  

## XLS
1) **XlsxCellBuilder** - we don't use this class directly but as a 
integral part of `InsertableXlsContent`.  

`cell(Row row, int colCount, XXX value)` - used to set value of type XXX
(eg. String, BigDecimal, Integer)  
`cell(Row row, int colCount, Date value, short dataFormat)` - used to 
set value of type `Date` with specific format (all possible formats are
defined in `XlsxDataFormat` - in `InsertableXlsContent`: `getFormat()`)  
```
getCellBuilder().cell(row, col, value).build();
getCellBuilder().cell(row, col, getFormat().dateHours()).build();
```  

`border()`, `alignment(HorizontalAlignment alignment)`
```
getCellBuilder().cell(row, col, value).alignment(HorizontalAlignment.CENTER).build();
getCellBuilder().cell(row, col, value).border().alignment(HorizontalAlignment.CENTER).build();
```  

`singleCellFontSize(int size)`, `foregroundColor(IndexedColors color)` - changes only in the cell we 
are working on (without any influence on the others)  
```
getCellBuilder().cell(row, col, value).singleCellFontSize(500).build(); 
getCellBuilder().cell(row, col, value).foregroundColor(IndexedColors.GOLD).build();
```

`setDefaultForegroundColor(IndexedColors color)` - changes permanently 
all cells constructed by instance of `XlsxCellBuilder` (still can be 
outshouted by using `foregroundColor`); changes are saved in 
`CellDefaults`; method doesn't allow chaining  
`getCellBuilder().setDefaultForegroundColor(IndexedColors.GREY_40_PERCENT);`
and then we have to use `fillPattern(FillPatternType type)` to color
specific cell with already set default foreground color
```getCellBuilder().cell(row, col, value).fillPattern(FillPatternType.SOLID_FOREGROUND).build();```

`build()` - after calling this method we construct cell with all set
features then reset all fields to default, eg. `CustomCellStyle.CellBorder.border` field
is set to `false`
```
public Cell build() {
    Cell cell = prepareCell();

    resetFields();

    return cell;
}
    
private void resetFields() {
    cellText = new CellText();
    cellFormat = new CellFormat();
    cellStyle = new CustomCellStyle();
}
```
more exemplary code of usages `XlsxCellBuilder` in packages:
`xlsx.books.sheet.first`, `xlsx.books.sheet.second`

---
2) **XlsxDataFormat** - cells in excel could have specific format (eg. 
date: `YYYY-MM-DD` or date with time: `YYYY-MM-DD hh:mm` - take a look 
at pt. 2)) - this class is simply the cache

---
3) **XlsxDataFormatType** - enum for excel format types  

---
4) **AbstractXlsxWriter** - base class for creating pdf file; usage:
```
XXX extends AbstractXlsxWriter
```
then we `@Override` method `prepare(Workbook workbook)`, where we construct 
document. Use method:  
`add(InsertableXlsSheet sheet)` to add sheet:
```
@Override
public void prepare(Workbook workbook) {
    add(new FirstSheet(bundles, workbook));
    add(new SecondSheet(bundles, workbook));
}
```
more exemplary code of usages `AbstractXlsxWriter` in class (test package)
: `XlsxGenerationTest`  

---
5) **InsertableXlsContent** - every content that is inserted into sheet 
should be defined in separate class extending `InsertableXlsContent`:  
```
XXX extends InsertableXlsContent
```
then we have to `@Override` methods:  
`void create()` (we have access to `Sheet` by `getSheet()`, 
and `BundleHandler` by `getBundles()`):
```
@Override
public void create() {
    int rowCount = 0;
    add(new FirstTable(getBundles(), getSheet(), rowCount));
    rowCount++;
    add(new SecondTable(getBundles(), getSheet(), rowCount));

}
```
more exemplary code of usages `InsertableXlsContent` in classes 
(test package): `BookCollectionSheetContent`, 
`BookCollectionSheetTitle`, `BookCollectionTable`, 
`SummarySheetContent`, `SummarySheetTitle`, `SummaryTable`  

---
6) **InsertableXlsSheet** - every sheet that is inserted into report 
should be defined in separate class extending `InsertableXlsSheet`:
```
XXX extends InsertableXlsSheet
```
then we have to `@Override` methods:  
`void create()` (we have access to `Sheet` by `getSheet()`, 
and `BundleHandler` by `getBundles()`),  
`String getBundleKeySheetName()` and  
`void setColumnWidthInSheet()`:
```
@Override
public void create() {
    int rowCount = 0;
    add(new SheetTitle(getBundles(), getSheet(), rowCount));

}

@Override
public String getBundleKeySheetName() {
    return "keyFromBundles";
}

@Override
public void setColumnWidthInSheet() {

}
```
more exemplary code of usages `InsertableXlsSheet` in classes (test 
package): `SummarySheet`, `BookCollectionSheet`  

## XML
1) **XmlDocumentBuilderChainImpl** - allows to create xml (by chaining
feature)  
to adding new elements / attributes we use methods:  
```
@Override
public XmlElementBuilderImpl element(String name) {
    return new XmlElementBuilderImpl(createElement(Objects.requireNonNull(name)), getElement());
}

@Override
public XmlElementBuilderImpl attribute(String name, String value) {
    super.addAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

    return this;
}

@Override
public XmlElementBuilderImpl up() {
    return new XmlElementBuilderImpl(super.up(1));
}
```
calling `element("name1").element("innerElementOfName1")` produces chain:
```
<name1>
    <innerElementOfName1/>
</name1>
```
so we have to provide method `up()` to escape from inside of the tag, so:  
calling `element("name1").element("innerElementOfName1").up().element("name2")`
produces:
```
<name1>
    <innerElementOfName1/>
</name1>
<name2/>
```
after all we called `build()`

more exemplary code of usages `XmlDocumentBuilderChainImpl` in class 
(test package): `ChainReportTypeXmlWriterShowcase`, 
`XmlDocumentBuilderChainImplTest`

---
2) **XmlDocumentBuilderStraightImpl** - allows to create xml in a 
"straight" way:  
Creating elements / attributes:
```
@Override
public XmlElementBuilderImpl element(String name) {
    return new XmlElementBuilderImpl(createElement(Objects.requireNonNull(name)));
}

@Override
public XmlElementBuilderImpl attribute(String name, String value) {
    super.addAttribute(Objects.requireNonNull(name), Objects.requireNonNull(value));

    return this;
}
```
inner elements:
```
public XmlElementBuilderImpl addInnerElement(String name) {
    innerElements.add(createElement(Objects.requireNonNull(name)));

    return this;
}

public XmlElementBuilderImpl addInnerElement(Element elem) {
    innerElements.add(Objects.requireNonNull(elem));

    return this;
}
```
Examples:  
to get
```
<name1>
    <innerElementOfName1/>
</name1>
```
we should call
```
Element node1 = createElement("node1")
                .addInnerElement(
                        createElement("innerElementOfName1")
                        .build())
                .build();
```
more exemplary code of usages `XmlDocumentBuilderStraightImpl` in class 
(test package): `StraightReportTypeXmlWriterShowcase`, 
`XmlDocumentBuilderStraightImpl`
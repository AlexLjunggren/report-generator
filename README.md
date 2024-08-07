## Report Generator ##

Annotate member variables or methods that will appear on the report

```java
@Reportable(headerName = "Name", column = "A")
```

Annotate reportable member variables or methods with formatters (*optional*)

```java
@DateFormatter(format = "yyyy-MM-dd")
@DecimalFormatter(format = "#.00")
@StringFormatter(format = Format.UPPERCASE)
@CurrencyFormatter(format = Currency.USD)
@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
@NullFormatter(replacementText = "No Data")
@CommaFormatter
@TrimFormatter
```

Instantiate and generate

```java
new CSVGenerator(List<?> data, char delimiter).generate(); // returns CSV String
new ExcelGenerator(List<?> data).generate(); // returns POI Workbook
```

## Auto Size ##

```java
@AutoSize
@Reportable(headerName = "Name", column = "A")
```

AutoSize coupled with Reportable will set the Excel columns to auto-adjust with column width to fit content

## Date Formatter ##

```java
@DateFormatter(format = "yyyy-MM-dd")
```
Data types supported
- java.util.Date  

Format used by java.text.SimpleDateFormat

## Decimal Formatter ##

```java
@DecimalFormatter(format = "#.00")
```

Data types supported 
- Integer
- Double
- Float
- Long

Format used by java.text.DecimalFormat  

## String Formatter ##

```java
@StringFormatter(format = Format.UPPERCASE)
```

Data types supported 
- String

Options  
- Format.UPPERCASE
- Format.LOWERCASE
- Format.CAPITALIZE
- Format.CAPITALIZE_FULLY

## Currency Formatter ##

```java
@CurrencyFormatter(format = Currency.USD)
```

Data types supported 
- Integer
- Double
- Float
- Long
- Big Decimal

Options   
- Format.USD

## Boolean Formatter ##

```java
@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
```

Arguments  
- falseText
- trueText

## Null Formatter ##

```java
@NullFormatter(replacementText = "No Data")
```

Data ytpes supported
- All Objects

Arguments
- replacementText

## Comma Formatter ##

```java
@CommaFormatter
```

Data types supported 
- Integer
- Double
- Long

## Trim Formatter ##

```java
@TrimFormatter
```

Data types supported 
- String

## HyperLink ##

```java
@Hyperlink
```

Creates a hyperlink out of the cell data

**Note:** Only applies to ExcelGenerator
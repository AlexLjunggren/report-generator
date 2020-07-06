## Report Generator ##

Annotate member variables that will appear on the report

```java
@Reportable(headerName = "Name", order = 1)
```

Annotate reportable member variables with formatters (*optional*)

```java
@DateFormatter(format = "yyyy-MM-dd")
@DecimalFormatter(format = "#.00")
@StringFormatter(format = Format.UPPERCASE)
@CurrencyFormatter(format = Currency.USD)
@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
@CommaFormatter
```

Instantiate and generate

```java
new CSVGenerator(List<?> data, char delimiter).generate();
new ExcelGenerator(List<?> data).generate();
```

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

Options   
- Format.USD

## Boolean Formatter ##

```java
@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
```

Arguments  
- falseText
- trueText

## Comma Formatter ##

```java
@CommaFormatter
```

Data types supported 
- Integer
- Double
- Long

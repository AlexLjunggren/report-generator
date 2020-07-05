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
@CurrencyFormatter(format = Format.USD)
@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
@CommaFormatter
```

Instantiate and generate

```java
new CSVGenerator(List<?> data, char delimiter).generate();
new ExcelGenerator(List<?> data).generate();
```

## Date Formatter ##

Format used by java.text.SimpleDateFormat

## Decimal Formatter ##

Format used by java.text.DecimalFormat

## String Formatter ##

- Format.UPPERCASE
- Format.LOWERCASE
- Format.CAPITALIZE
- Format.CAPITALIZE_FULLY

## Currency Formatter ##

- Format.USD

## Boolean Formatter ##

- falseText
- trueText

## Comma Formatter ##

No arguments


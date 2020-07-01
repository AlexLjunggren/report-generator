## Report Generator ##

Annotate member variables that will appear on the report

```java
@Reportable(headerName = "Name", order = 1)
```

Annotate reportable member variables with formatters (*optional*)

```java
@DateFormatter(format = "yyyy-MM-dd")
@StringFormatter(format = Format.UPPERCASE)
```

Instantiate and generate

```java
new CSVGenerator(List<?> data, char delimiter).generate();
new ExcelGenerator(List<?> data).generate();
```

# csv4j
csv4j reads files in variations of the Comma Separated Value (CSV) format and map to POJO.

## Usage


```java
@CSVModel
public class Person  {

    @CSVColumn(name = "id",type = ColumnType.GUID)
    private UUID uuid;

    @CSVColumn(name = "first_name")
    private String firstName;

    @CSVColumn(name = "last_name")
    private String lastName;

    @CSVColumn(name = "birthday",type = ColumnType.DATE)
    private Date birthday;

    @CSVColumn(name = "email")
    private String email;

    @CSVColumn(name = "gender")
    private String gender;

    @CSVColumn(name = "city")
    private String city_city;

    @CSVColumn(name = "address")
    private String address;
    
}
```
.
```csv
|id                                  |first_name|last_name|birthday |email               |gender|city    |address |
|------------------------------------|----------|---------|---------|--------------------|------|--------|--------|
|a3a49331-4295-4c9e-890c-decb97bb5906|Hussein   |Kubiczek |6/20/2002|hkubiczek0@google.cn|Male  |Danghara|Apt 1235|

```

```java
public class Main {
    public static void main(String[] args) {
        CSVManager<Person> csvManager =
                null;
        try {
            csvManager = new CSVManager<>(Person.class,"MOCK_DATA.csv");
            List<Person> list = csvManager.load();
            list.forEach(System.out::println);
        } catch (CSVHeaderNotFoundException | ElementManyAnnotatedException e) {
            throw new RuntimeException(e);
        }
    }
}

```

```java
#Output
Person{uuid=a3a49331-4295-4c9e-890c-decb97bb5906, firstName='Hussein', lastName='Kubiczek', birthday=Thu Jun 20 00:00:00 AZST 2002, email='hkubiczek0@google.cn', gender='Male', city_city='Danghara', address='Apt 1235'}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
MIT License

Copyright (c) [2023] [RockDevs]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

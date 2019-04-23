package com.zhenghaikj.shop.entity;

public class GetImageCheckCode {

    /**
     *  "Id":"831d918e5d7e480891dc886a488dbf29",
     *  "FileContents":"iVBORw0KGgoAAAANSUhEUgAAAFUAAAAeCAYAAABdalL1AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANSSURBVGhD7Zc7kuIwEIY5GVU+DJEvsSHZhByBDC6wGRMTkk7OnGFLI1lq9cMtW2rjnZmC4K/Cstzu/vohs/n89+FeeqxeUFfQC+oKekFdQetDve3c5s9G1+moP/PL1Q71vnddC4wpqLPaubNm84erHmoLHAL9etkOa93lndsrKSRN2GPPZj9+LvB6qCnYEOD5snfXYf3dvR04gEk1tzvaz2BrxomSmP+ZBEOlbt3bnd6Lgfc3uoaCSg3ie46uzwETjcAj2PA82ENbip2DT7qE35xQu6qhnk/EQU0hkGEvqV4fyDzUVEFQXdkOEQAS9qJkkqnAl6k9NPGPqeb2gypLQClVHhGFmgPJEKU9JZEEKk8QSSTT1nVzUNmMFjEYq/uBUKMyrOQQgOEQ/D4fDK9M3d4gUqmqPVnlo5lagJr38fsQQ/XhKrQcqmhXGXQJKgWlXhNRG/E3hUCqSxsdReFz6BuxxdbbZIdamIEy6FmoB9+iKQg9EAg02hxD9QJfqtsVx8WoGsEv+Y4GLYc6BMIzjA6JWScribbfDX7zvZCUeM0PwbGdghjs0vyFhEIsyhiqlB2q2q7gMDgE15asawBxrfcVNtvqio+YpJ3rM9zo3/Xuq7bQgS1aDnUQQJRZVqCy5yrEkpbsVwY8OnDku08e7PCbVKVaLG0yQ9VPSBl0uZVyxYCqQIG9Cnl7Z/r5lYGSzywYOeTdS0/+oMVQGbBR6xAIIvPFA2xKeXbyJIEvzBaZs/1fBNqfCDSlKk1+CZmhYqVNQC3OJzIbW5wfQaCVW5rb+K7Of2lQn3NhZHvKuDLICLXwchk0u0YA3eVocz7Zk60ZE1yyBe+l9+Mazlp/LcdRkmUMGKGCo7QNERpAZfPJOx72BgDdaZ9O3jaoapvPVpfmaxIkPYh107KKtUFV2jx84shKwrkLAich0Dan9YoEAAq0oOIIEvKfU3h/IhEVWgiV/xsCAVScu0HBQe/4nVR0E9QSvBkA4Csoz88J1SaiIPNBxVpHKEIFCNp9y0zl9vKsC59FU5XKpPhEIPMi4PdaZIe6QDgW2tqfajxa2lqVznsO0+4T6Fugfr+wYuHQwyS1JUfTk0Ilcz3PzbRmnKNUTwoVRA9NCdmuJ4e6hj7cF2d0/X8nZcRcAAAAAElFTkSuQmCC",
     *  "ContentType":"image/png","
     *  FileDownloadName":""
     */


    private String Id;
    private String FileContents;
    private String ContentType;
    private String FileDownloadName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFileContents() {
        return FileContents;
    }

    public void setFileContents(String fileContents) {
        FileContents = fileContents;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getFileDownloadName() {
        return FileDownloadName;
    }

    public void setFileDownloadName(String fileDownloadName) {
        FileDownloadName = fileDownloadName;
    }
}

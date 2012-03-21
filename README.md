## Introduction

This project presents an example implementation of the Argos API in Java. Using this library, along with access to a 3.x version of Argos (www.evisions.com), you can enable your Java applications to execute reports, and return the result as a byte[].

You can use it in desktop applications to save files to disk, or you can write servlets, JSPs, or RESTful web services to execute reports and return the completed results to web clients.

I encourage you to study the source code, and if you prefer to work in a different flavor of web middleware such as ASP.NET or Ruby, then do not hesitate to copy the logic exhibited here into your preferred programming language. I intend to keep this library maintained as the API abilities and features evolve within the Argos product.

## Dependencies

This library only requires on dependency in your application, and that is the Google Guava library (http://code.google.com/p/guava-libraries). I have been using Google Guava as my "swiss army knife" for programming, and many of its packages replace several releases from the Apache Commons. The appropriate JAR file that I used for testing is in the /lib directory

## Basic Example

This sample is also available as the class edu.fhda.argos.tests.V3ReportRunner

**Argos 3.x**

Executing reports from an Argos 3.x platform is really simple. Once you have configured a report to be enabled for API access, create an ArgosV3Report object in your application with the API key as part of the constructor. Configure your MAPS server, the referer for security, and then add optional parameters, or change the desired format from the default of PDF.

```java
// Create a report object to get a PDF from Argos
ArgosV3Report myReport = new ArgosV3Report("RPPNAB4APA4I5DZMEUNWIURZY52YEF6CXU4DWHCAB25KV36U27MWFCEYXDXIZA7TWEBRC4VMNKWHM");
myReport.setMAPSServer("YOUR_MAPS_SERVER");
myReport.setMAPSReferrer("YOUR_REFERER");;
myReport.addParameter("CourseTerm", "201232");
myReport.addParameter("CourseCRN", "00010");

// Run a test
// 1. Report executes correctly
myReport.executeReport();
System.out.format("MAPS response = %d; report URL: %s\n", myReport.getHTTPResponseCode(), myReport.getMAPSReportURL());
if(myReport.getHTTPResponseCode() == 500) {
    System.out.println(myReport.getHTTPResponse());
}

// 2. Resulting report file can be downloaded
byte[] reportData = myReport.downloadReport();
Files.write(reportData, new File("argos_api_test.pdf"));
System.out.format("Acquired report file; bytes = %d\n", reportData.length);
```

## License and Warranty

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
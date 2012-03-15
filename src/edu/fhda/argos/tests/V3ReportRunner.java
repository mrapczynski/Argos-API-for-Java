package edu.fhda.argos.tests;

import com.google.common.io.Files;
import edu.fhda.argos.ArgosV3Report;

import java.io.File;

/**
 * Simple class with only one main method as a basic example of how to configurate and run an Argos report
 * remotely from a Java application.
 * @version 1.0
 * @author Matt Rapczynski
 */
public class V3ReportRunner {
    
    public static void main(String[] args) throws Exception {
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
    }
    
}

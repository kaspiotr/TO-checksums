import java.io.*;
import java.security.MessageDigest;

public class ChecksumSHA1Standalone {

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA1");
        int numRead;
        long fileSize = 0;
        do {
            numRead = fis.read(buffer);
            fileSize += numRead;
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        System.out.println(fileSize);
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getSHA1Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    //Zack Hemsey - Graven Image.mp3
    //9b24d3d5b0b27e966d6cd58b1e7c716d61d1a082
    //9b24d3d5b0b27e966d6cd58b1e7c716d61d1a082

    //isapi_redirect-1.2.30.dll
    //cca9176f72ff56beb1f76c21b1d7daa6be192890
    //cca9176f72ff56beb1f76c21b1d7daa6be192890

    //test.txt
    //fe266a09f64d7d58cc58bbdf40f9859c7b6900dd
    //fe266a09f64d7d58cc58bbdf40f9859c7b6900dd


    public static void main(String args[]) {
        try {
//            System.out.println(getSHA1Checksum("resources/Zack Hemsey - Graven Image.mp3"));
            System.out.println(getSHA1Checksum("resources/isapi_redirect-1.2.30.dll"));
//            System.out.println(getSHA1Checksum("resources/test.txt"));
            // output :
            //  cca9176f72ff56beb1f76c21b1d7daa6be192890
            // ref :
            //   http://tomcat.apache.org/
            //          dev/dist/tomcat-connectors/
            //            jk/binaries/win32/jk-1.2.30/
            //              isapi_redirect-1.2.30.dll.sha1
            //
            //  cca9176f72ff56beb1f76c21b1d7daa6be192890 *isapi_redirect-1.2.30.dll

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
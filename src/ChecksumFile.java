import java.io.*;

public class ChecksumFile {
    private IChecksumStrategy strategy;
    private FileIterator fileIterator;

    public ChecksumFile(IChecksumStrategy strategy, FileIterator fileIterator) {
        this.strategy = strategy;
        this.fileIterator = fileIterator;
    }

    /**
     * Calculates checksum of a specified by FileIterator file instance according to given implementation
     * of ICheckSumStrategy interface
     * @return String with file's checksum
     */
    public String calcSum() {
        return strategy.calculate(this.fileIterator);
    }

    public static void main(String[] args) {
        try {
            ChecksumFile checksumFile = new ChecksumFile(new ChecksumSHA1(), new FileIterator("resources/test.txt"));
//            ChecksumFile checksumFile = new ChecksumFile(new ChecksumSHA1(), new FileIterator("resources/isapi_redirect-1.2.30.dll"));
//            ChecksumFile checksumFile = new ChecksumFile(new ChecksumSHA1(), new FileIterator("resources/Zack Hemsey - Graven Image.mp3"));
            System.out.println(checksumFile.calcSum());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

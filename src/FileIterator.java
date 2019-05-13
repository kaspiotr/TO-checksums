import java.io.*;
import java.util.Iterator;

public class FileIterator implements Iterator {
    private RandomAccessFile raf;
    private long position = 0;
    private File file;

    public FileIterator(String filePath) throws FileNotFoundException {
        this.file = new File(filePath);
        this.raf = new RandomAccessFile(file, "r");
    }

    @Override
    public boolean hasNext() {
        long fileLength = file.length();
        if (fileLength == 0L) return false;
        return position < fileLength;
    }

    @Override
    public Byte next() {
        if(this.hasNext()) {
            try {
                raf.seek(position++);
                return (byte) raf.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FileIterator fileIterator = null;
        try {
            fileIterator = new FileIterator("resources/isapi_redirect-1.2.30.dll");
            while(fileIterator.hasNext()) {
                System.out.println(fileIterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

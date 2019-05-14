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

    /**
     * Returns true if the iteration has more elements.
     * @return true if next would return a byte of file rather than throwing an exception
     */
    @Override
    public boolean hasNext() {
        long fileLength = file.length();
        if (fileLength == 0L) return false;
        return position < fileLength;
    }

    /**
     * Returns the next element in the iteration.
     * @return next Byte of file in the interation
     */
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
}

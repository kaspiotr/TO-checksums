import java.security.MessageDigest;

public class ChecksumSHA1 implements IChecksumStrategy {

    private static byte[] createChecksum(FileIterator fileIterator) throws Exception {
        MessageDigest complete = MessageDigest.getInstance("SHA1");
        while(fileIterator.hasNext()) {
            complete.update(new byte[]{fileIterator.next()}, 0, 1);
        }
        return complete.digest();
    }

    private static String getSHA1Checksum(FileIterator fileIterator) throws Exception {
        byte[] b = createChecksum(fileIterator);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    @Override
    public String calculate(FileIterator fileIterator) {
        try {
            return getSHA1Checksum(fileIterator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PROBLEM OCCURRED WHILE CALCULATING SHA1 CHECKSUM";
    }

}

public interface IChecksumStrategy {
    /**
     * Calculates checksum of a file given by a FileIterator instance
     * @param fileIterator
     * @return String with a checksum of a file
     */
    String calculate(FileIterator fileIterator);
}

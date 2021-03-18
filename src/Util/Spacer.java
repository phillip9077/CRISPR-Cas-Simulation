package Util;

/**
 * Represents the sections of viral DNA a bacteria stores in its CRISPR-Cas system, which is the
 * adaptive immunity aspect of CRISPR-Cas systems.
 */
public class Spacer extends DNA {

  /**
   * Default constructor for the {@code Spacer} class.
   *
   * @param parentStrand The parent strand of the DNA
   * @param tempStrand   The template strand of the DNA
   */
  public Spacer(String parentStrand, String tempStrand) {
    super(parentStrand, tempStrand);
  }

  @Override
  public String endonucleaseRNACleave() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("A spacer should not be calling this method!");
  }
}

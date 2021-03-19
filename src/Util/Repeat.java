package Util;

/**
 * Represents the palindromic repeats characteristic of CRISPR-Cas systems.
 */
public class Repeat extends DNA {

  /**
   * Default constructor for the {@code Repeat} class.
   *
   * @param parentStrand The parent strand of the DNA
   * @param tempStrand   The template strand of the DNA
   */
  public Repeat(String parentStrand, String tempStrand) {
    super(parentStrand, tempStrand);
  }

  @Override
  public String endonucleaseRNACleave() throws UnsupportedOperationException {
    // only a small portion of the repeats are transcribed as a part of the mature crRNA
    // in our case I made it so it just transcribes the last four nucleotides of the repeats
    // biologically I am not certain how this process actually works out
    StringBuilder result = new StringBuilder();
    for (int i = this.tempStrand.length() - 4; i < this.tempStrand.length(); i++) {
      result.append(DNA.BASE_PAIRS.get(this.tempStrand.charAt(i)));
    }
    return result.toString();
  }

}

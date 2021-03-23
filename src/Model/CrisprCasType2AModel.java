package Model;

import Util.DNA;
import Util.Repeat;
import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an Model.ICrisprCasModel. Technically, there are different kinds of CRISPR-Cas
 * systems, but for simplicity's sake it is generalized to the most studied one: CRISPR-Cas Type
 * II-A. Genetically, in CRISPR loci the repeats are practically identical in length and sequence.
 * The spacers seem to have the identical length, but sequence content varies.
 */
public class CrisprCasType2AModel implements ICrisprCasModel {

//   FIELDS:
//     - must have some sort of list to represent the CRISPR array (queue, FIFO)
//       * spacers represented by actual DOUBLE-STRANDED sequences (might need a custom
//         data structure to represent DNA???)
//       * repeats represented by a simple repeating palindromic sequence
//
//     - an array to store the "processed mature crRNAs" (can just be a List of Strings)
//       * probably not a field, instead just takes CRISPR array and produces a list of crRNAs
//       * each crRNA would be represented by a String sequence

  private List<DNA> crisprArr;
  private final DNA repeat;

  /**
   * Default constructor for the {@code CrisprCasType2Model} class.
   */
  public CrisprCasType2AModel() {
    this.crisprArr = new ArrayList<>();
    this.repeat = new Repeat("CTAGATCAGT", "GATCTAGTCA");
    this.crisprArr.add(this.repeat);
  }

  @Override
  public void adaptation(DNA virus) throws NullPointerException, IllegalArgumentException {
    if (virus == null) {
      throw new NullPointerException("Provided virus is null");
    } else if (this.crisprArrayContains(virus)) {
      throw new IllegalArgumentException("Virus already exists in CRISPR array");
    }
    // very roughly mimics the action of the Cas1-Cas2 complex
    this.crisprArr.add(virus);
    this.crisprArr.add(this.repeat);
  }

  @Override
  public List<String> biogenesis() {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < this.crisprArr.size() - 1; i += 2) {
      String crRNA = "";
      // first DNA is a repeat for sure so we only want a small section from it
      crRNA += this.crisprArr.get(i).endonucleaseRNACleave();
      // the other two we can just call DNA2RNA since we want the full RNA sequence for both
      crRNA += this.crisprArr.get(i + 1).DNA2RNA();
      crRNA += this.crisprArr.get(i + 2).DNA2RNA();
      result.add(crRNA);
    }
    return result;
  }

  @Override
  public boolean interference(List<String> crRNAList, DNA virus) throws NullPointerException {
    if (crRNAList == null || virus == null) {
      throw new NullPointerException("The list or DNA cannot be null");
    }
    // TODO: ADD EXCEPTION TO HANDLE IF THE GIVEN VIRUS DOESN'T EXIST IN crRNAList
    String sequence = virus.getParentStrand();
    for (int i = 0; i < crRNAList.size(); i++) {
      if (crRNAList.get(i).contains(sequence)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean crisprArrayContains(DNA virus) {
    return this.crisprArr.contains(virus);
  }

  @Override
  public List<DNA> getCRISPRArray() {
    // It would be safe to add the same DNA objects to another list because the data inside the
    // DNA class is immutable
    List<DNA> copy = new ArrayList<>();
    for (int i = 0; i < this.crisprArr.size(); i++) {
      copy.add(this.crisprArr.get(i));
    }
    return copy;
  }
}

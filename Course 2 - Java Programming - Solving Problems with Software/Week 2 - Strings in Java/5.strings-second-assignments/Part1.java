/**
 * Programming Exercise: Finding Many Genes - Part 1
 * 
 * @author Ginny Dang 
 * @version July 6th, 2022
 */

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        // Find the index of the start codon
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        // Find the index of each possible stop codon
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // Find the index of the closest stop codon
        int minIndex = 0;
        
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        
        if (minIndex == -1) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }    
    
    public void testFindStopCodon() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("test finished");
    }
    
    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println("test finished");
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test() {
        //      ATGv  TAAv  ATG   v  v  TGA
        //testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        //testOn("");
        //      ATGv  v  v  v  TAAv  v  v  ATGTAA
        //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOn("AATGCTAACTAGCTGACTAAT");
    }
}

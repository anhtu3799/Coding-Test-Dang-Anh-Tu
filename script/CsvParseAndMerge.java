import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParseAndMerge {
    public static Map<String, Sentence> engSentences = new HashMap<>();
    public static Map<String, Sentence> vieSentences = new HashMap<>();
    static class Sentence {
        String id;
        String lang;
        String text;

        Sentence(String id, String lang, String text) {
            this.id = id;
            this.lang = lang;
            this.text = text;
        }
    }
    // Method to read sentences from CSV file
    static void readSentences(String fileName, String engSens, String vieSens) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            //uncomment để test vì độ dài file csv qá lớn
//            i++;
//            if(i == 10) break;
            String[] parts = line.split("\t");
            String id = parts[0];
            String lang = parts[1];
            String text = parts[2];
            if(engSens.equals(lang)){
                engSentences.put(id, new Sentence(id, lang, text));
            }else if(vieSens.equals(lang)){
                vieSentences.put(id, new Sentence(id, lang, text));
            }else continue;
        }
        reader.close();
    }

    // Method to read links between sentences
    static List<String[]> readLinks(String filename) throws IOException {
        List<String[]> links = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            //uncomment để test vì độ dài file csv qá lớn
//            i++;
//            if(i == 10) break;
            String[] parts = line.split("\t");
            links.add(parts);
        }
        reader.close();
        return links;
    }
    // Method to generate English-Vietnamese translation CSV
    static void generateTranslationCSV(Map<String, Sentence> englishSentences, List<String[]> links,
                                       Map<String, Sentence> vietnameseSentences, String outputFilename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));
        writer.write("id,text,audio_url,translate_id,translate_text\n"); // Writing CSV header

        for (String[] link : links) {
            String englishId = link[0];
            String vietnameseId = link[1];

            Sentence englishSentence = englishSentences.get(englishId);
            Sentence vietnameseSentence = vietnameseSentences.get(vietnameseId);

            if (englishSentence != null && vietnameseSentence != null) {
                String audioUrl = String.format("https://audio.tatoeba.org/sentences/%s/%s.mp3",
                        englishSentence.lang, englishSentence.id);
                writer.write(String.format("%s,%s,%s,%s,%s\n", englishId, englishSentence.text, audioUrl,
                        vietnameseId, vietnameseSentence.text));
            }
        }
        writer.close();
    }


    public static void main(String[] args) {
        //độ dài của các file quá lớn dễ bị crash.
        //EM đã test thử và gen file thành công
        try{
            readSentences("\\Entrytest_Dang_Anh_Tu\\sentences.csv","eng","vie");
            List<String[]> links = readLinks("\\Entrytest_Dang_Anh_Tu\\links.csv");
            generateTranslationCSV(engSentences,links,vieSentences,"\\Entrytest_Dang_Anh_Tu\\merge_result.csv");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

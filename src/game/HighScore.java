package src.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HighScore {

    static String highScoreFilePath = "./res/highscore/highscore.txt";
    public static TreeMap<Integer, String> hightScores = new TreeMap<>(Collections.reverseOrder());

    public static int[] s = new int[5];
    public static String[] n = new String[5];
    public static int size = -1;

    public static void loadHighScore() {
        BufferedReader in;
        try {
            // in = new BufferedReader(new FileReader(highScoreFilePath));
            // String fromText;
            // while ((fromText = in.readLine()) != null) {
            // // System.out.println(fromText);
            // String[] line = fromText.split(" ");
            // String highScore_Name = "";
            // for (int i = 0; i < line.length - 1; i++) {
            // highScore_Name += line[i] + " ";
            // }
            // Integer highSCore_Score = Integer.parseInt(line[line.length - 1]);
            // hightScores.put(highSCore_Score, highScore_Name);
            // }
            // in.close();

            in = new BufferedReader(new FileReader(highScoreFilePath));

            String fromText;
            while ((fromText = in.readLine()) != null) {
                // String[] line = fromText.split(" ", 2);
                // String highScore_Name = "";
                // for (int i = 0; i < line.length - 2; i++) {
                // highScore_Name += line[i] + " ";
                // }
                // highScore_Name += line[line.length - 2];
                // int score = Integer.parseInt(line[line.length - 1]);
                size++;
                s[size] = in.read();
                n[size] = in.readLine();
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHightScore(int score, String name) {
        // hightScores.put(score, name);
        try {
            // String toText = "";
            // BufferedWriter out = new BufferedWriter(new FileWriter(highScoreFilePath,
            // false));
            // int k = 0;
            // if (hightScores.size() > 5) {
            // k = 5;
            // } else {
            // k = hightScores.size();
            // }
            // // System.out.println(hightScores.size());

            // for (Map.Entry<Integer, String> entry : hightScores.entrySet()) {
            // if (k > 0) {
            // toText += entry.getValue() + " " + entry.getKey() + "\n";
            // k--;
            // }
            // }
            // out.write(toText);
            // // System.out.println(toText);
            // out.close();

            int[] s2 = new int[5];
            String[] n2 = new String[5];
            int i2 = -1;
            int k = 0;
            boolean d = false;

            // UPDATE
            for (int j = 0; j <= size; j++) {
                if (i2 + 1 <= 4) {
                    if (score < s[j]) {
                        i2++;
                        s2[i2] = s[j];
                        n2[i2] = n[j];
                    } else if ((score == s[j] && name.compareTo(n[j]) < 0) || (score > s[j])) {
                        d = true;
                        i2++;
                        k = j;
                        s2[i2] = score;
                        n2[i2] = name;
                        break;
                    }
                } else
                    break;
            }

            if (d) {
                for (int j = k; j <= size; j++) {
                    if (i2 + 1 <= 4) {
                        i2++;
                        s2[i2] = s[j];
                        n2[i2] = n[j];
                    }
                }
            } else {
                if (i2 + 1 <= 4) {
                    i2++;
                    d = false;
                    s2[i2] = score;
                    n2[i2] = name;
                }
            }

            System.out.println(i2);

            for (int j = 0; j <= i2; j++) {
                if (n2[j] != null)
                    System.out.println(n2[j]);
            }

            String str = "";
            BufferedWriter out = new BufferedWriter(new FileWriter(highScoreFilePath));

            for (int j = 0; j <= i2; j++) {
                if (n2[j] != null)
                    str += s2[j] + "\n" + n2[j] + "\n";
            }

            out.write(str);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

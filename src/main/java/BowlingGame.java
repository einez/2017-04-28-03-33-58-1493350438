
public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        int[] grades = new int[12];
        for (int i = 0; i < grades.length; i++) {
            grades[i] = 0;
        }
        bowlingCode = bowlingCode.substring(0, bowlingCode.length() - 1) + "|" + bowlingCode.charAt(bowlingCode.length() - 1) + "|0|0";
        String[] performs = bowlingCode.split("(\\|)+");
        for (int i = 0; i < 10; i++) {
            grades[i] = roundGrade(performs[i]);
        }
        for (int i = 0; i < 10; i++) {
            if (performs[i].contains("X")) {
                grades[i] += attachGrade(performs[i + 1], performs[i + 2]);
            } else if (performs[i].contains("/")) {
                grades[i] += attachGrade(performs[i + 1]);
            }
        }
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            ret += grades[i];
        }
        return ret;
    }

    public int roundGrade(String value) {
        if (value.contains("X") || value.contains("/")) {
            return 10;
        }
        value = value.replace('-', '0');
        return value.charAt(0) - '0' + value.charAt(1) - '0';
    }

    public int attachGrade(String nextRound) {
        return nextRound.replace('-', '0').replace('X', (char) ('0' + 10)).charAt(0) - '0';
    }

    public int attachGrade(String nextRound1, String nextRound2) {
        if (nextRound1.contains("/")) {
            return 10;
        }
        return attachGrade(nextRound1) + attachGrade((nextRound1 + nextRound2).substring(1));
    }
}

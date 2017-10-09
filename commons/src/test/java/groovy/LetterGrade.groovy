package groovy;

import org.junit.Test

import static org.junit.Assert.assertEquals

class LetterGrade {

    private int type = 1;

    def gradeFromScore(score) {
        switch (score) {
            case 90..100: return "A"
            case 80..<90: return "B"
            case 70..<80: return "C"
            case 60..<70: return "D"
            case 0..<60: return "F"
            case ~"[ABCDFabcdf]": return score.toUpperCase()
            default: throw new IllegalArgumentException("Invalid score: ${score}")

        }
    }

    @Test
    void test_letter_grades() {
        def lg = new LetterGrade()
        assertEquals("A", lg.gradeFromScore(92))
        assertEquals("B", lg.gradeFromScore(85))
        assertEquals("D", lg.gradeFromScore(65))
        assertEquals("F", lg.gradeFromScore("f"))
    }
}
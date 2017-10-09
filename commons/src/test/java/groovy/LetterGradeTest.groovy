package groovy

import org.junit.Test

import static org.junit.Assert.assertEquals

class LetterGradeTest {

    @Test
    void test_letter_grades() {
        def lg = new LetterGrade();
        assertEquals("A", lg.gradeFromScore(92));
        assertEquals("B", lg.gradeFromScore(85));
        assertEquals("D", lg.gradeFromScore(65));
        assertEquals("F", lg.gradeFromScore("f"));
    }
}
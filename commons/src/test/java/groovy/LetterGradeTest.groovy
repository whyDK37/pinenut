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
        lg.getType()

    }

//    def filter(list, p) {
//        if (list.size() == 0) return list
//        if (p(list.head()))
//            [] + list.head() + filter(list.tail(), p)
//        else
//            filter(list.tail(), p)
//    }
    def filter(list, p) {
        def new_list = []
        list.each { i ->
            if (p(i))
                new_list << i
        }
        new_list
    }

    @Test
    void test() {

        l = filter(1..20, { n -> n % 2 == 0 })
        println(l)

    }
}
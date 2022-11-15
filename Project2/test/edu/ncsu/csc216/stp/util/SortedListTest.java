package edu.ncsu.csc216.stp.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.SortedList;

class SortedListTest {

    @Test
    void testNewSortedList() {
        SortedList<String> list = new SortedList<String>();
        assertEquals(list.size(), 0);
    }

    @Test
    void testAddElement() {
        SortedList<String> list = new SortedList<String>();
        list.add("bb");
        list.add("dd");
        list.add("cc");
        list.add("aa");
        assertEquals("aa", list.get(0));
        assertEquals("bb", list.get(1));
        assertEquals("cc", list.get(2));
        assertEquals("dd", list.get(3));
    }
@Test
    void testRemove() {
        SortedList<String> list = new SortedList<String>();
        list.add("bb");
        list.add("aa");
        list.add("cc");
        list.add("dd");
        assertEquals(4, list.size());
        list.remove(2);
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
    }

    @Test
    void testAddTestPlan() {
        SortedList<TestPlan> list = new SortedList<TestPlan>();
        list.add(new TestPlan("test 1"));
        list.add(new TestPlan("test 3"));
        list.add(new TestPlan("test"));
        list.add(new TestPlan("TEST 4"));
        list.add(new TestPlan("test 2"));
        assertEquals(list.get(0).getTestPlanName(), "test");
        assertEquals(list.get(1).getTestPlanName(), "test 1");
        assertEquals(list.get(2).getTestPlanName(), "test 2");
        assertEquals(list.get(3).getTestPlanName(), "test 3");
        assertEquals(list.get(4).getTestPlanName(), "TEST 4");
    }

    @Test
    void testContains() {
        SortedList<TestPlan> list = new SortedList<TestPlan>();
        TestPlan tp1 = new TestPlan("banana");
        TestPlan tp2 = new TestPlan("apple");
        TestPlan tp3 = new TestPlan("orange");
        TestPlan tp4 = new TestPlan("eggplant");
        list.add(tp1);
        list.add(tp2);
        list.add(tp3);
        list.add(tp4);
        assertTrue(list.contains(tp3));
    }
}
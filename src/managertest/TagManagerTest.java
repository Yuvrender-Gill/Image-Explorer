package managertest;

import tagmanager.Tag;
import tagmanager.TagManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TagManagerTest {

    /**
     * The tagmanager to test.
     */
    private TagManager tagManager;

    @BeforeEach
    void setUpTagManager() {
        tagManager = new TagManager();
    }

    @AfterEach
    void tearDownTagManager() {
        tagManager = null;
    }

    @Test
    void testTagManagerAddTag() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test2");
        tagManager.addTag(t1);
        tagManager.addTag(t2);
        assertTrue(tagManager.getTagList().size() == 2);
        assertEquals(tagManager.getTagList().get(0), t1);
        assertEquals(tagManager.getTagList().get(1), t2);
    }

    @Test
    void testTagManagerAddDuplicateTag() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test");
        tagManager.addTag(t1);
        tagManager.addTag(t2);
        assertTrue(tagManager.getTagList().size() == 1);
        assertEquals(tagManager.getTagList().get(0), t1);
    }

    @Test
    void testTagManagerRemoveTag() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test");
        tagManager.addTag(t1);
        tagManager.removeTag(t2);
        assertTrue(tagManager.getTagList().size() == 0);
    }

    @Test
    void testTagManagerRemoveNonPresentTag() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test 2");
        tagManager.addTag(t1);
        tagManager.removeTag(t2);
        assertTrue(tagManager.getTagList().size() == 1);
        assertEquals(tagManager.getTagList().get(0), t1);
    }

    @Test
    void testTagManagerResetTags() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test2");
        tagManager.addTag(t1);
        tagManager.addTag(t2);
        tagManager.resetTags();
        assertTrue(tagManager.getTagList().size() == 0);
    }

    @Test
    void testTagManagerContainsTag() {
        Tag t1 = new Tag("test");
        Tag t3 = new Tag("test");
        tagManager.addTag(t1);
        assertTrue(tagManager.containsTag(t3));
        assertTrue(tagManager.getTagList().size() == 1);
    }

    @Test
    void testTagManagerToStringEmpty() {
        assertEquals(tagManager.toString(), "The tag manager is empty.");
    }

    @Test
    void testTagManagerToString() {
        Tag t1 = new Tag("test");
        tagManager.addTag(t1);
        assertEquals(tagManager.toString(), "Tag: @test Created at : " + t1.getTimeStamp() + "\n");
    }

    @Test
    void testTagManagerGetTagList() {
        Tag t1 = new Tag("test");
        Tag t2 = new Tag("test2");
        tagManager.addTag(t1);
        tagManager.addTag(t2);
        ArrayList<Tag> actualTagList = new ArrayList<>();
        actualTagList.add(t1);
        actualTagList.add(t2);
        assertEquals(tagManager.getTagList(), actualTagList);
    }

}

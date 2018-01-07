package managertest;

import imagemanager.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.rules.TemporaryFolder;

class ImageManagerTest {

    /**
     * The imagemanager to test.
     */
    private ImageManager imageManager;

    /**
     * A temporary folder with images to test.
     */
    private TemporaryFolder tempFolder;

    @BeforeEach
    void setUpImageManager() throws IOException{
        imageManager = new ImageManager();
        tempFolder = new TemporaryFolder();
        tempFolder.create();
    }

    @AfterEach
    void tearDownImageManager() {
        imageManager = null;
        tempFolder.delete();
    }

    @Test
    void testImageManagerAddImage() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("image.jpeg"));
        ImageFile i2 = new ImageFile(tempFolder.newFile("imageFile.png"));
        imageManager.addImage(i1);
        imageManager.addImage(i2);
        assertTrue(imageManager.getImageList().size() == 2);
        assertTrue(imageManager.getImageList().get(0).equals(i1));
        assertTrue(imageManager.getImageList().get(1).equals(i2));
    }

    @Test
    void testImageManagerAddDuplicateImage() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("image.jpeg"));
        ImageFile i2;
        i2 = i1;
        imageManager.addImage(i1);
        imageManager.addImage(i2);
        assertTrue(imageManager.getImageList().size() == 1);
    }

    @Test
    void testImageManagerRemoveImage() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("imageTest.jpeg"));
        ImageFile i2 = new ImageFile(tempFolder.newFile("imageFile2.png"));
        imageManager.addImage(i1);
        imageManager.addImage(i2);
        imageManager.removeImage(i1);
        assertTrue(imageManager.getImageList().size() == 1);
        assertTrue(imageManager.getImageList().get(0).equals(i2));
    }

    @Test
    void testImageManagerRemoveNonPresentImage() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("imageTest.jpeg"));
        ImageFile i2 = new ImageFile(tempFolder.newFile("imageFile2.png"));
        imageManager.addImage(i1);
        imageManager.removeImage(i2);
        assertTrue(imageManager.getImageList().size() == 1);
        assertTrue(imageManager.getImageList().get(0).equals(i1));
    }

    @Test
    void testImageManagerGetImageList() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("image.jpeg"));
        ImageFile i2 = new ImageFile(tempFolder.newFile("imageFile2.png"));
        imageManager.addImage(i1);
        imageManager.addImage(i2);
        ArrayList<ImageFile> actualImageList = new ArrayList<>();
        actualImageList.add(i1);
        actualImageList.add(i2);
        assertEquals(imageManager.getImageList(), actualImageList);
    }

    @Test
    void testImageManagerClearImageList() throws ClassNotFoundException, IOException{
        ImageFile i1 = new ImageFile(tempFolder.newFile("image.jpeg"));
        ImageFile i2 = new ImageFile(tempFolder.newFile("imageFile2.png"));
        imageManager.addImage(i1);
        imageManager.addImage(i2);
        assertTrue(imageManager.getImageList().size() == 2);
        imageManager.clearImageList();
        assertTrue(imageManager.getImageList().size() == 0);
    }
}

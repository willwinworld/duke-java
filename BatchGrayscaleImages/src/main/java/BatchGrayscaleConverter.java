import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchGrayscaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            ImageResource gray = makeGray(inImage);
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String[] args){
//        BatchGrayscaleConverter instance = new BatchGrayscaleConverter();
//        instance.selectAndConvert();
    }
}

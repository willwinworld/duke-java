import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversions {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource gray = makeGray(inImage);
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String[] args){
        BatchInversions instance = new BatchInversions();
        instance.selectAndConvert();
    }
}

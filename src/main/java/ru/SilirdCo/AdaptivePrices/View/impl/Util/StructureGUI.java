package ru.SilirdCo.AdaptivePrices.View.impl.Util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import ru.SilirdCo.AdaptivePrices.Util.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StructureGUI {
    private static final Logger logger = LoggerFactory.getLogger(StructureGUI.class);

    private final static String iconsLocation = "/icons/";
    public final static ImageView defaultIcon = getIcon("default");

    public static ImageView getIcon(String str)  {
        Resource resource =
                (new AnnotationConfigApplicationContext()).getResource("classpath:" + StructureGUI.iconsLocation + str + ".png");

        Image image = null;
        javafx.scene.image.ImageView imageView = new ImageView();

        try {
            image = new Image(resource.getInputStream());
        }
        catch (FileNotFoundException ex) {
            if (!str.equals("default")) {
                imageView = getIcon("default");
            }
            else {
                return imageView;
            }
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        imageView.setImage(image);

        return imageView;
    }
}

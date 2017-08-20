package core.pdf.builder;

import com.google.common.base.Preconditions;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;

/**
 * Created by mtumilowicz on 2017-07-12.
 */
public class ImageBuilder {
    
    private final String path;

    private Size size;

    private Position position;

    private ImageBuilder(String path) {
        this.path = path;
    }

    public ImageBuilder widthAndHeight(float width, float height) {
        this.size = new Size(width, height);
        
        return this;
    }

    public ImageBuilder position(float abscissa, float ordinate) {
        this.position = new Position(abscissa, ordinate);
        
        return this;
    }
    
    public Image build() {
        Image icon;
        try {
            Preconditions.checkState(StringUtils.isNotEmpty(path));
            icon = new Image(ImageDataFactory.create(path));
        } catch (MalformedURLException e) {
            throw new ImageBuilderException(e.getLocalizedMessage());
        }

        setSize(icon);
        
        setPosition(icon);
        
        return icon;
    }
    
    private void setSize(Image icon) {
        if (size != null) {
            icon.scaleToFit(size.width, size.height);
        }
    }
    

    private void setPosition(Image icon) {
        if (position != null) {
            icon.setFixedPosition(position.abscissa, position.ordinate);
        }
    }
    
    public static final class Factory {
        public static final ImageBuilder get(String path) {
            Preconditions.checkArgument(StringUtils.isNotEmpty(path));
            
            return new ImageBuilder(path);
        }
    }
    
    private static final class ImageBuilderException extends RuntimeException {
        public ImageBuilderException(String message) {
            super(message);
        }
    }
    
    private static final class Size {
        private final float width;
        private final float height;

        private Size(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }
    
    private static final class Position {
        private final float abscissa;
        private final float ordinate;

        public Position(float abscissa, float ordinate) {
            this.abscissa = abscissa;
            this.ordinate = ordinate;
        }
    }
}

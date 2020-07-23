package ca.attractors.images;

import javax.swing.*;
import java.net.URL;

public class ImageLoader {
	private String filename;
	
	public ImageLoader(String aFilename) {
		filename = aFilename;
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon(getResourceURL());
	}

	private URL getResourceURL() {
		return getClass().getClassLoader().getResource(getResourceName());
	}
	
	private String getResourceName() {
		return getImagesRoot() + getFilename();
	}
	
	private String getImagesRoot() {
		return getPackageName().replace('.', '/') + "/";
	}

	private String getPackageName() {
		return getClass().getPackage().getName();
	}

	private String getFilename() {
		return filename;
	}
}
